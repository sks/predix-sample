package com.sks.predix.sample.controller.advice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.VndErrors;
import org.springframework.hateoas.VndErrors.VndError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ch.qos.logback.classic.Logger;

@ControllerAdvice
public class TodoControllerAdvice {

	private static Logger logger = (Logger) LoggerFactory
			.getLogger(TodoControllerAdvice.class);

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<VndError> catchEntityNotFoundException(
			Exception exception, HttpServletRequest request) {
		logger.error("Could not finish the request " + request.getRequestURI(),
				exception);
		return convertToVndError(HttpStatus.NOT_FOUND,
				getLinkForRequest(request.getRequestURI()),
				HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.name());
	}

	private Link getLinkForRequest(String requestURI) {
		return new Link(requestURI);
	}

	private ResponseEntity<VndError> convertToVndError(HttpStatus status,
			Link links, String message, String logref) {
		VndError vndError = new VndError(logref, message, links);
		return new ResponseEntity<VndErrors.VndError>(vndError, status);
	}

}
