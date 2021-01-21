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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ejs.os.api.input.ClienteInput;
import br.com.ejs.os.domain.model.Cliente;
import br.com.ejs.os.domain.model.StatusOrdemSevicoEnum;
import br.com.ejs.os.domain.model.dto.ClienteDTO;
import br.com.ejs.os.domain.repository.ClienteCustomRepository;
import br.com.ejs.os.domain.service.CadastroClienteService;
import br.com.ejs.os.domain.service.ClienteComentarios;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@Autowired
	private ClienteCustomRepository cliCustom;
	
	@Autowired
	private ClienteComentarios clienteComentarios;
	
	@PostMapping
	public ResponseEntity<?> adicionar( @Valid @RequestBody ClienteInput in) throws Exception{
		ClienteDTO dto = cadastroClienteService.adicionar(in);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar( @RequestBody Cliente cliente) throws Exception{
		ClienteDTO dto = cadastroClienteService.atualizar(cliente);
		return ResponseEntity.ok(dto);
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
	
	@GetMapping("contar")
	public ResponseEntity<?> contar(@RequestParam(name = "like") String like){
		return ResponseEntity.ok(cliCustom.contarClientes(like));
	}
	
	@GetMapping("comentarios")
	public ResponseEntity<?> listarComentarios( @RequestParam(name = "clienteId") Long clienteId, //
			@RequestParam(name = "status") StatusOrdemSevicoEnum status){
		return ResponseEntity.ok(clienteComentarios.listarComentarios(clienteId, status));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
