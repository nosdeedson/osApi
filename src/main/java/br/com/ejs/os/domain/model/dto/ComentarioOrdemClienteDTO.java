package br.com.ejs.os.domain.model.dto;

import java.time.OffsetDateTime;

public class ComentarioOrdemClienteDTO {

	private Long clienteId;
	private Long ordemServicoId;
	private Long comentarioId;
	private String descricao;
	private OffsetDateTime dataEnvio;
	
	public ComentarioOrdemClienteDTO(Long clienteId, Long ordemServicoId, Long comentarioId, String descricao,
			OffsetDateTime dataEnvio) {
		super();
		this.clienteId = clienteId;
		this.ordemServicoId = ordemServicoId;
		this.comentarioId = comentarioId;
		this.descricao = descricao;
		this.dataEnvio = dataEnvio;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getOrdemServicoId() {
		return ordemServicoId;
	}

	public void setOrdemServico(Long ordemServicoId) {
		this.ordemServicoId = ordemServicoId;
	}

	public Long getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(Long comentarioId) {
		this.comentarioId = comentarioId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public OffsetDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(OffsetDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
		
	
	
}
