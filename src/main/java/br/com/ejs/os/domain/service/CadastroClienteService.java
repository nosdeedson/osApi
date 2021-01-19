package br.com.ejs.os.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ejs.os.api.input.ClienteInput;
import br.com.ejs.os.domain.exception.BusinessException;
import br.com.ejs.os.domain.exception.NaoEncotrado;
import br.com.ejs.os.domain.model.Cliente;
import br.com.ejs.os.domain.model.dto.ClienteDTO;
import br.com.ejs.os.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper mapper;
		
	
	public ClienteDTO adicionar(ClienteInput in) throws Exception {
		Optional<Cliente> clienteExistente = clienteRepository.findByEmail(in.getEmail());
		
		if( clienteExistente.isPresent() ) {
			throw new BusinessException("Email já usado.");
		} 
		
		var novoCliente = this.inputToEntity(in);
		
		novoCliente = clienteRepository.save(novoCliente);
		return this.entityParaDTO(novoCliente);
		
				
		
	}
	
	public ClienteDTO atualizar( Cliente cliente) {
		
		Cliente clienteExiste = clienteRepository.findById(cliente.getId())
				.orElseThrow( () -> new NaoEncotrado("Cliente não encontrado"));
		clienteExiste.setEmail(cliente.getEmail());
		clienteExiste.setNome(cliente.getNome());
		clienteExiste.setTelefone(cliente.getTelefone());
		
		cliente = clienteRepository.save(cliente);
		
		return this.entityParaDTO(cliente);
		
	}
	
	public ClienteDTO buscar(Long clienteId) {
		var cliente = clienteRepository.findById(clienteId);
		
		if( cliente.isEmpty()) {
			throw new NaoEncotrado("Cliente não encontrado.");
		}
		return this.entityParaDTO(cliente.get());
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
		
		return this.listaEntityParaDTO(clientes);		
	}
	
	private Cliente inputToEntity( ClienteInput in) {
		return mapper.map(in, Cliente.class);
	}

	private ClienteDTO entityParaDTO( Cliente cliente) {
		return mapper.map(cliente, ClienteDTO.class);
	}
	
	private List<ClienteDTO> listaEntityParaDTO( List<Cliente> clientes){
		return clientes.stream()
				.map(cliente -> entityParaDTO(cliente))
				.collect(Collectors.toList());
	}
	
}
