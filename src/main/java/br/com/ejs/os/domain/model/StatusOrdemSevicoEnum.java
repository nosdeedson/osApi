package br.com.ejs.os.domain.model;

public enum StatusOrdemSevicoEnum {
	
ABERTA("Aberta"), FINALIZADA("Finalizada"), CANCELADA("Cancelada");
	
	private String descricao;
	StatusOrdemSevicoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
