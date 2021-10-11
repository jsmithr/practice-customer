package pragma.photoservice.exceptions;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * Se genera cuando no encuentra el recurso solicitado
	 * @param	exc	Excepción generada recurso no encontrado
	 * @return 	Construye una respuesta BAD_REQUEST
	 */
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ErrorResponse> handleException(NoSuchElementException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	/**
	 * Se genera cuando se intenta insertar un dato duplicado y que está como marcado único en la entidad
	 * @param	exc	Excepción generada por valores duplicados
	 * @return 	Construye una respuesta BAD_REQUEST
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	protected ResponseEntity<ErrorResponse> handleException(DuplicateKeyException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	/**
	 * Se genera cuando al método le han pasado un argumento inválido
	 * @param	exc	Excepción generada el argumento es inválido
	 * @return 	Construye una respuesta BAD_REQUEST
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	/**
	 * Aplica para los datos no vienen completos según la definición de los campos que existen en la entidad correspondiente
	 * @param	exc	Excepción generada por campo vacío, nulo, excede los mínimos, entre otras validaciones
	 * @return 	Construye una respuesta BAD_REQUEST, crea una lista de los mensajes de validación por cada campo
	 * 			Intancia un RuntimeException con el mensaje "Datos inválidos"
	 */
	@ExceptionHandler(InvalidDataException.class)
	protected ResponseEntity<ErrorResponse> handleException(InvalidDataException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errors = exc.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		return buildResponseEntity(httpStatus, new RuntimeException("Datos inválidos"), errors);
	}
	@ExceptionHandler( MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleException( MethodArgumentNotValidException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errors = exc.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		System.out.println("MethodArgumentNotValidException -> exc = " + exc);

		return buildResponseEntity(httpStatus, new RuntimeException("Datos inválidos"), errors);
	}
	@ExceptionHandler(WebExchangeBindException.class)
	protected ResponseEntity<ErrorResponse> handleException(WebExchangeBindException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errors = exc.getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		System.out.println("WebExchangeBindException -> exc = " + exc);
		return buildResponseEntity(httpStatus, new RuntimeException("Datos inválidos"), errors);
	}

	/**
	 * Aplica cuando en el URL se envia un argumento invalido, por ejemplo String por Integer
	 * @param	exc	Excepción generada por tipo de argumento inválido
	 * @return 	Construye una respuesta BAD_REQUEST e instancia un RuntimeException con el mensaje "Tipo de argumento inválido"
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, new RuntimeException("Tipo de argumento inválido"));
	}

	/**
	 * Se genera cuando la excepción no se controla en ninguno de los anteriores métodos
	 * @param	exc	Excepción
	 * @return 	Construye una respuesta INTERNAL_SERVER_ERROR
	 * 			Instancia un RuntimeException con el mensaje "Ops! Hubo un problema, inténtalo de nuevo"
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception exc) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return buildResponseEntity(httpStatus, exc);
	}

	/**
	 * Construye la respuesta que se retorna al cliente
	 * @param	exc	Excepción
	 * @return 	Construye una respuesta INTERNAL_SERVER_ERROR, BAD_REQUEST
	 */
	private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
		return buildResponseEntity(httpStatus, exc, null);
	}

	/**
	 * Construye la respuesta que se retorna al cliente
	 * @param	exc	Excepción
	 * @return 	Construye una respuesta INTERNAL_SERVER_ERROR, BAD_REQUEST
 * 				Agrega lista de errores
	 */
	private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exc.getMessage());
		error.setCode(httpStatus.value());
		error.setTimestamp(new Date());
		error.setErrors(errors);
		return new ResponseEntity<>(error, httpStatus);

	}

}

