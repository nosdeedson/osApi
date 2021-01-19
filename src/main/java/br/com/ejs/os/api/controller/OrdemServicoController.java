package br.com.ejs.os.api.controller;

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

import br.com.ejs.os.api.input.OrdemServicoInput;
import br.com.ejs.os.domain.service.GerirOrdemServicoService;

@RestController
@RequestMapping("ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	GerirOrdemServicoService gerirOrdemServico;
	
	@PostMapping
	public ResponseEntity<?> adicionar( @RequestBody OrdemServicoInput in ){
		return  ResponseEntity.ok(gerirOrdemServico.adicionar(in));
	}
	
	@PutMapping("{ordemServicoId}")
	public ResponseEntity<?> atualizar ( @PathVariable Long ordemServicoId, @RequestBody OrdemServicoInput in){
		return ResponseEntity.ok(gerirOrdemServico.atualizar(ordemServicoId, in));
	}
	
	@GetMapping("{ordemServicoId}")
	public ResponseEntity<?> buscar( @PathVariable Long ordemServicoId ){
		return ResponseEntity.ok(gerirOrdemServico.buscar(ordemServicoId));
	}
	
	@PutMapping("cancelar")
	public ResponseEntity<?> cancelar(@RequestParam Long ordemServicoId){
		return ResponseEntity.ok(gerirOrdemServico.cancelar(ordemServicoId));
	}
	
	@DeleteMapping("{ordemServicoId}")
	public ResponseEntity<?> excluir(@PathVariable Long ordemServicoId){
		return ResponseEntity.ok(gerirOrdemServico.excluir(ordemServicoId));
	}
	
	@PutMapping("finalizar")
	public ResponseEntity<?> finalizar(@RequestParam Long ordemServicoId){
		return ResponseEntity.ok(gerirOrdemServico.finalizar(ordemServicoId));
	}
	
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(gerirOrdemServico.listar());
	}
	
	@GetMapping("{/clienteId}")
	public ResponseEntity<?> listarPorCliente(@RequestParam Long clienteId){
		return ResponseEntity.ok(gerirOrdemServico.listarPorCliente(clienteId));
	}
	
}
