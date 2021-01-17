package br.com.ejs.os.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ejs.os.api.input.ClienteInput;
import br.com.ejs.os.domain.model.dto.ClienteDTO;
import br.com.ejs.os.domain.service.CadastroClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@PostMapping
	public ResponseEntity<?> adicionar( @Valid @RequestBody ClienteInput in) throws Exception{
		ClienteDTO dto = cadastroClienteService.adicionar(in);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping
	public void atualizar( @RequestBody ClienteInput in) throws Exception{
		this.adicionar(in);
	}

	@GetMapping("{clienteId}")
	public ResponseEntity<?> bucar(@PathVariable Long clienteId){
		var clienteDTO = cadastroClienteService.buscar(clienteId);
		return ResponseEntity.ok(clienteDTO);
	}
	
	@DeleteMapping("{clienteId}")
	public ResponseEntity<?> excluir( @PathVariable Long clienteId){
		String retorno = cadastroClienteService.excluir(clienteId);
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<ClienteDTO> clientesDTO = cadastroClienteService.listar();
		return ResponseEntity.ok(clientesDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
