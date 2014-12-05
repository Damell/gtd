/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

import GTD.DL.DLDAO.ConstraintException;
import GTD.DL.DLDAO.DAOException;
import GTD.DL.DLDAO.DAOServerException;
import GTD.DL.DLDAO.ItemNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author simon
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler
{
//    @ExceptionHandler(DAOException.class)
//    public ResponseEntity<?> handleDAOException(DAOException de, WebRequest request) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		
//		if (de.getCause() instanceof ConstraintViolationException
//				|| de instanceof ItemNotFoundException) { // TODO steklsim is this enough?
//			return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
//		} else {
//			return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//    }
//	
//	@ExceptionHandler(SecurityException.class)
//    public ResponseEntity<?> handleSecurityException(SecurityException se, WebRequest request) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		
//		return new ResponseEntity<>(null, httpHeaders, HttpStatus.FORBIDDEN);
//    }
	
	@ExceptionHandler(ConstraintException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleConstraintException(ConstraintException ce) 
	{
		
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleItemNotFoundException(ItemNotFoundException infe) 
	{
//        HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		
//		if (de.getCause() instanceof ConstraintViolationException
//				|| de instanceof ItemNotFoundException) { // TODO steklsim is this enough?
//			return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
//		} else {
//			return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
    }
	
	@ExceptionHandler(DAOServerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleDAOServerException(DAOServerException dse) 
	{
		
	}
	
	@ExceptionHandler(SecurityException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleSecurityException(SecurityException se) 
	{
//        HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		
//		return new ResponseEntity<>(null, httpHeaders, HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler(JsonProcessingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleJsonProcessingException(JsonProcessingException jpe) 
	{
		
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleJsonProcessingException(NullPointerException npe) 
	{
		return npe.getMessage();
	}
}
