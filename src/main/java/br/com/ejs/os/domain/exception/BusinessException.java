package br.com.ejs.os.domain.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessException( String message) {
		super(message);
	}
}
