package br.com.ejs.os.api.handleException;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ejs.os.domain.exception.BusinessException;
import br.com.ejs.os.domain.exception.NaoEncotrado;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		var excecao = new Excecao( status.value(), OffsetDateTime.now(), //
				ex.getMessage());
		
		return handleExceptionInternal(ex, excecao, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NaoEncotrado.class)
	public ResponseEntity<?> handleNaoEncontradoException( NaoEncotrado ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		
		var excecao = new Excecao(status.value(), OffsetDateTime.now(), ex.getMessage());
		
		return handleExceptionInternal(ex, excecao, new HttpHeaders(), status, request);
	}
	
}
