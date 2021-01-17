package br.com.ejs.os.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ejs.os.api.input.ClienteInput;
import br.com.ejs.os.domain.model.Cliente;

@Service
public class ClienteInputToCliente {
	
	@Autowired
	private ModelMapper mapper;
	
	public Cliente inputToCliente( ClienteInput in) {
		return mapper.map(in, Cliente.class);
	}

}
