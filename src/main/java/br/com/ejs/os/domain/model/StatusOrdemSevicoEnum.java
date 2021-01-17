package br.com.ejs.os.domain.model;

public enum StatusOrdemSevicoEnum {
	
ABERTA("Aberta 1"), FINALIZADA("Finalizada 2"), CANCELADA("Cancelada 3");
	
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
