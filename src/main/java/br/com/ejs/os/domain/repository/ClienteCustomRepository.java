package br.com.ejs.os.domain.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ejs.os.domain.model.Cliente;
import br.com.ejs.os.domain.model.Comentario;
import br.com.ejs.os.domain.model.StatusOrdemSevicoEnum;
import br.com.ejs.os.domain.model.dto.ComentarioOrdemClienteDTO;

@Repository
@Transactional
public class ClienteCustomRepository {
	
	@Autowired
	private EntityManager em;
	
	public Long contarClientes(String like) {
		
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cliente> contaCliente = criteria.from(Cliente.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.like(contaCliente.get("nome"), "%" + like ));
		
		Predicate[] predicateArray = predicates.toArray(new Predicate[predicates.size()]);
		
		criteria.where(predicateArray);		
		criteria.select(builder.count(contaCliente));
		
		return this.em.createQuery(criteria).getSingleResult();
	}

	public List<ComentarioOrdemClienteDTO> listarComentarios(Long clienteId, StatusOrdemSevicoEnum status) {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<ComentarioOrdemClienteDTO> criteria = builder.createQuery(ComentarioOrdemClienteDTO.class);
		Root<Comentario> comentario = criteria.from(Comentario.class);
		var ordemServico = comentario.join("ordemServico", JoinType.LEFT);
		var cliente = ordemServico.join("cliente", JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(cliente.get("id"), clienteId));
		predicates.add(builder.equal(ordemServico.get("status"), status));
		
		Predicate[] predicateArray = predicates.toArray(new Predicate[predicates.size()]);
		
		criteria.where(predicateArray);
		criteria.select(builder.construct(ComentarioOrdemClienteDTO.class, //
				cliente.get("id").alias("clienteId"), //
				ordemServico.get("id").alias("ordemServicoId"), //
				comentario.get("id").alias("comentarioId"), //
				comentario.get("descricao").alias("descricao"), //
				comentario.get("dataEnvio").alias("dataEnvio") //
				));
		var executeQuery = this.em.createQuery(criteria);
		
		return executeQuery.getResultList();
	}
	

}
