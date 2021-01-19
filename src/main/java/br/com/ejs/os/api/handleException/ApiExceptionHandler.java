package br.com.ejs.os.api.handleException;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
		var status = HttpStatus.OK;
		
		var excecao = new Excecao(status.value(), OffsetDateTime.now(), ex.getMessage());
		
		return handleExceptionInternal(ex, excecao, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final var excecao = new Excecao(status.value(), OffsetDateTime.now(), "Um ou mais campos estão inválidos.");
		var campos = new ArrayList<Campo>();
		
		for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
			String mensagem = erro.getDefaultMessage();
			String atributo = ((FieldError)erro).getField();
			
			campos.add(new Campo(atributo, mensagem));
		}
		
		excecao.setCampos(campos);
		return super.handleExceptionInternal(ex, excecao, headers, status, request);
	}
}
