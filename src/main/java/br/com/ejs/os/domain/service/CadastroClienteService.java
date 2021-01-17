package br.com.ejs.os.domain.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ejs.os.api.input.ClienteInput;
import br.com.ejs.os.domain.exception.BusinessException;
import br.com.ejs.os.domain.exception.NaoEncotrado;
import br.com.ejs.os.domain.model.Cliente;
import br.com.ejs.os.domain.model.dto.ClienteDTO;
import br.com.ejs.os.domain.repository.ClienteRepository;
import br.com.ejs.os.helper.ClienteInputToCliente;
import br.com.ejs.os.helper.ClienteToClienteDTO;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteInputToCliente clienteInputToCliente;
	
	@Autowired
	private ClienteToClienteDTO clienteToClienteDTO;
	
	
	public ClienteDTO adicionar(ClienteInput in) throws Exception {
		
		Optional<Cliente> clienteExistente = clienteRepository.findByEmail(in.getEmail());
		
		if( clienteExistente.isPresent() && clienteExistente.get().naoPodeAdicionar(in.getEmail()) ) {
			throw new BusinessException("Email já usado.");
		} 
		/**
		 * arrumar para atualizar e adicionar
		 */
		var cliente = clienteInputToCliente.inputToCliente(in);
		
		try {
			cliente = clienteRepository.save(cliente);
			return clienteToClienteDTO.clienteToclienteDTO(cliente);
		} catch (Exception e) {
			throw new BusinessException("Falha ao salvar.");
		}		
		
	}
	
	public ClienteDTO buscar(Long clienteId) {
		var cliente = clienteRepository.findById(clienteId);
		
		if( cliente.isEmpty()) {
			throw new NaoEncotrado("Cliente não encontrado.");
		}
		return clienteToClienteDTO.clienteToclienteDTO(cliente.get());
	}
	
	public String excluir( Long clienteId) {
		if( clienteRepository.existsById(clienteId) ) {
			clienteRepository.deleteById(clienteId);
			return "Excluído";
		}
		throw new NaoEncotrado(" Cliente não encontrado.");
	}
	
	public List<ClienteDTO> listar(){
		
		List<Cliente> clientes = clienteRepository.findAll();
		
		if ( clientes.isEmpty()) {
			throw new NaoEncotrado("Não há clientes");
		}
		
		return clienteToClienteDTO.listaClienteToClienteDTO(clientes);		
	}
	
	
	
}
