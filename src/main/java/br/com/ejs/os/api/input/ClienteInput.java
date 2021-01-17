package br.com.ejs.os.api.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClienteInput {
	
	@NotBlank(message = "Nome obirgatório")
	@Size(max = 60)
	private String nome;
	
	@NotBlank(message = "Email obrigatório")
	@Email(message = "Email inválido.")
	private String email;
	
	@NotBlank(message = "Telefone obrigatório")
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
