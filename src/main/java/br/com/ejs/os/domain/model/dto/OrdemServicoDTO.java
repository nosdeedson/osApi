package br.com.ejs.os.domain.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ejs.os.domain.model.Comentario;
import br.com.ejs.os.domain.model.StatusOrdemSevicoEnum;

@JsonInclude(content = Include.NON_NULL)
public class OrdemServicoDTO {
	
	private Long id;
	private String descricao;
	private BigDecimal preco;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	private StatusOrdemSevicoEnum status;
	private ClienteDTO cliente;
	private List<Comentario> comentarios = new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public String getStatus() {
		return this.status.getDescricao();
	}
	public void setStatus(StatusOrdemSevicoEnum status) {
		this.status = status;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	

}
