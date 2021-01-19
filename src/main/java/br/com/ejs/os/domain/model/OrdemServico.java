package br.com.ejs.os.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Descrição não pode estar vazia.")
	private String descricao;
	
	@DecimalMin(value = "1.0")
	private BigDecimal preco;
	
	@Column(name = "data_abertura")
	private OffsetDateTime dataAbertura;
	
	@Column(name = "data_finalizacao")
	private OffsetDateTime dataFinalizacao;
	
	@ManyToOne
	private Cliente cliente;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "ordemServico")
	private List<Comentario> comentarios = new ArrayList<>();
	
	@Column(name = "status")
	private StatusOrdemSevicoEnum status;
	
	public OrdemServico() {
		super();
	}

	public OrdemServico( String descricao, BigDecimal preco, OffsetDateTime dataAbertura,
			OffsetDateTime dataFinalizacao) {
		super();
		this.descricao = descricao;
		this.preco = preco;
		this.dataAbertura = dataAbertura;
		this.dataFinalizacao = dataFinalizacao;
	}
	
	public boolean podeFinalizarOuCancelar() {
		return StatusOrdemSevicoEnum.ABERTA.getDescricao().equals(this.status.getDescricao());
	}
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public StatusOrdemSevicoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemSevicoEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
