package br.com.ejs.os.api.input;

import javax.validation.constraints.NotBlank;

public class ComentarioInput {

	@NotBlank(message = "O id da ordem deve ser informado.")
	private Long ordemServicoId;
	
	@NotBlank(message = "Uma descrição é necessária.")
	private String descricao;
	
	public Long getOrdemServicoId() {
		return ordemServicoId;
	}
	public void setOrdemServicoId(Long ordemServicoId) {
		this.ordemServicoId = ordemServicoId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
