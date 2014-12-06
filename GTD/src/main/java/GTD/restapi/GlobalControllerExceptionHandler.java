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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
	private final int LEVEL_TRACE = 1;
	private final int LEVEL_DEBUG = 2;
	private final int LEVEL_INFO = 3;
	private final int LEVEL_WARNING = 4;
	private final int LEVEL_ERROR = 5;
	
	protected Logger logger = LoggerFactory.getLogger(TaskRestController.class);
	private MessageSource messageSource;

	public MessageSource getMessageSource()
	{
		return messageSource;
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}
	
	
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
	public void handleConstraintException(ConstraintException ce, WebRequest wr) 
	{
		logException(LEVEL_INFO, ce, wr);
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleItemNotFoundException(ItemNotFoundException infe, WebRequest wr) 
	{
		logException(LEVEL_INFO, infe, wr);
//		logger.info(getMessageSource().getMessage("restApi.exceptions.general", null, null), 
//				infe.getClass().getSimpleName(),
//				infe.getMessage(), 
//				wr.getDescription(true));
		
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
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleDAOServerException(DAOServerException dse, WebRequest wr) 
	{
		logException(LEVEL_ERROR, dse, wr);
	}
	
	@ExceptionHandler(SecurityException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleSecurityException(SecurityException se, WebRequest wr) 
	{
		logException(LEVEL_INFO, se, wr);
//        HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		
//		return new ResponseEntity<>(null, httpHeaders, HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler(JsonProcessingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleJsonProcessingException(JsonProcessingException jpe, WebRequest wr) 
	{
		logException(LEVEL_INFO, jpe, wr);
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleJsonProcessingException(NullPointerException npe, WebRequest wr) 
	{
		logException(LEVEL_INFO, npe, wr);
	}
	
	/**
	 * Logs exception and request description
	 * @param level
	 * @param e
	 * @param wr 
	 */
	private void logException(int level, Throwable e, WebRequest wr) 
	{
		switch (level) {
			case LEVEL_TRACE:
				logger.trace(getMessageSource().getMessage("restApi.exceptions.general", null, null), 
						e.getClass().getSimpleName(), e.getMessage(), wr.getDescription(true));
				break;
			case LEVEL_DEBUG:
				logger.debug(getMessageSource().getMessage("restApi.exceptions.general", null, null), 
						e.getClass().getSimpleName(), e.getMessage(), wr.getDescription(true));
				break;
			case LEVEL_INFO:
				logger.info(getMessageSource().getMessage("restApi.exceptions.general", null, null), 
						e.getClass().getSimpleName(), e.getMessage(), wr.getDescription(true));
				break;
			case LEVEL_WARNING:
				logger.warn(getMessageSource().getMessage("restApi.exceptions.general", null, null), 
						e.getClass().getSimpleName(), e.getMessage(), wr.getDescription(true));
				break;
			case LEVEL_ERROR:
				logger.error(getMessageSource().getMessage("restApi.exceptions.general", null, null), 
						e.getClass().getSimpleName(), e.getMessage(), wr.getDescription(true));
				break;
			default:
				logger.error("Unknown loggging level: " + level);
		}
	}
}
