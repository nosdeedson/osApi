package br.com.ejs.os.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ejs.os.domain.exception.NaoEncotrado;
import br.com.ejs.os.domain.model.StatusOrdemSevicoEnum;
import br.com.ejs.os.domain.model.dto.ComentarioOrdemClienteDTO;
import br.com.ejs.os.domain.repository.ClienteCustomRepository;

@Service
public class ClienteComentarios {
	
	@Autowired
	private ClienteCustomRepository cliCustom;
	
	
	public List<ComentarioOrdemClienteDTO> listarComentarios(Long clienteId, StatusOrdemSevicoEnum status){
		List<ComentarioOrdemClienteDTO> comentarios = cliCustom.listarComentarios(clienteId, status);
		if ( comentarios.isEmpty())
			throw new NaoEncotrado("Não há comentários.");
		return comentarios;
	}

}
