package br.com.ejs.os.domain.repository;

import java.time.OffsetDateTime;
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

import br.com.ejs.os.domain.model.OrdemServico;
import br.com.ejs.os.domain.model.dto.OrdemServicoDTO;

@Repository
@Transactional
public class OrdemServicoCustomRepository {

	@Autowired
	private EntityManager em;
	
	public List<OrdemServicoDTO> listarPorPeriodo(OffsetDateTime dataInicio, OffsetDateTime dataFim,
			Long ordemServicoId) {
		
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<OrdemServicoDTO> criteria = builder.createQuery(OrdemServicoDTO.class);
		Root<OrdemServico> ordemServico = criteria.from(OrdemServico.class);
		var cliente = ordemServico.join("cliente", JoinType.INNER);
		var comentario = ordemServico.join("comentarios", JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(ordemServico.get("id"), ordemServicoId));
		predicates.add(builder.greaterThanOrEqualTo(ordemServico.get("dataAbertura"), dataInicio));
		predicates.add(builder.lessThanOrEqualTo(ordemServico.get("dataAbertura"), dataFim));
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		criteria.select(builder.construct(OrdemServicoDTO.class, //
				ordemServico.get("id"),//
				ordemServico.get("descricao"), //
				ordemServico.get("preco"), //
				ordemServico.get("dataAbertura"), //
				ordemServico.get("dataFinalizacao"), //
				ordemServico.get("status"), //
				ordemServico.get("cliente"), //
				ordemServico.get("comentarios")
				));
		
		return this.em.createQuery(criteria).getResultList();
	}

}
