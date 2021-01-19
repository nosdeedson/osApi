package br.com.ejs.os.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ejs.os.domain.model.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
	
	List<OrdemServico> findByCliente_Id(Long clienteId);
	
}
