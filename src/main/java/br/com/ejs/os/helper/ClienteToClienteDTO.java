package br.com.ejs.os.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ejs.os.domain.model.Cliente;
import br.com.ejs.os.domain.model.dto.ClienteDTO;

@Service
public class ClienteToClienteDTO {
	
	@Autowired
	private ModelMapper mapper;

	public ClienteDTO clienteToclienteDTO( Cliente cliente) {
		return mapper.map(cliente, ClienteDTO.class);
	}
	
	public List<ClienteDTO> listaClienteToClienteDTO( List<Cliente> clientes){
		return clientes.stream()
				.map(cliente -> clienteToclienteDTO(cliente))
				.collect(Collectors.toList());
	}
}
