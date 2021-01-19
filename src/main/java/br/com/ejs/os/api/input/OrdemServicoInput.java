package br.com.ejs.os.api.input;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import br.com.ejs.os.domain.model.OrdemServico;

public class OrdemServicoInput {
	
	@NotBlank(message = "Descricao não pode estar vazia.")
	private String descricao;
	
	@DecimalMin(value = "1.0", message = "O preco deve ser informado.")
	private BigDecimal preco;
	
	@NotBlank(message = "cliente deve ser informado.")
	private Long idCliente;
		
	public OrdemServico toEntity() {
		OrdemServico ordem = new OrdemServico();
		ordem.setDescricao(this.descricao);
		ordem.setPreco(this.preco);
		return ordem;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Long getidCliente() {
		return idCliente;
	}
	
	public void setClienteId(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	

}
