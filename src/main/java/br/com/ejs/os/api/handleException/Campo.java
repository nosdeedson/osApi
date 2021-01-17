package br.com.ejs.os.api.handleException;

public class Campo {
	
	private String campo;
	private String message;
	public Campo(String campo, String message) {
		super();
		this.campo = campo;
		this.message = message;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
