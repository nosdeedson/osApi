package br.com.ejs.os.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ejs.os.api.input.ComentarioInput;
import br.com.ejs.os.domain.service.GerirOrdemServicoService;

@RestController
@RequestMapping("ordens-servico/comentarios")
public class ComentarioController {

	@Autowired
	private GerirOrdemServicoService gerirOrdem;
	
	@PostMapping
	public ResponseEntity<?> adicionar( @RequestBody  ComentarioInput in){
		return ResponseEntity.ok(gerirOrdem.adicionar(in));
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar( @RequestParam(name = "comentarioId") Long comentarioId, @RequestParam(name = "descricao") String descricao){
		return ResponseEntity.ok(gerirOrdem.atualizar(comentarioId, descricao));
	}
	
	@GetMapping("{ordemServicoId}")
	public ResponseEntity<?> listar( @PathVariable Long ordemServicoId){
		return ResponseEntity.ok(gerirOrdem.listar(ordemServicoId));
	}
}
