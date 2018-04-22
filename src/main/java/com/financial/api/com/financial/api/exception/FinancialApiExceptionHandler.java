package com.financial.api.com.financial.api.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.financial.api.com.financial.api.service.exception.PeopleNotExistOrNotActiveException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by netof on 03/03/2018.
 *
 */
@ControllerAdvice
public class FinancialApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String messageUser = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
        String messageDeveloper = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> erros = Arrays.asList(new Error(messageUser,messageDeveloper));
        return handleExceptionInternal(ex, new Error(messageUser, messageDeveloper),
                headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<Error> errosList = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, errosList,
                headers, HttpStatus.BAD_REQUEST, request);

    }

//    @ExceptionHandler({EmptyResultDataAccessException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public void handleEmptyResultDataAccessException(){
//
//    }

    //OR

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("resource.not.found", null, LocaleContextHolder.getLocale());
        String msgDev = ex.toString();
        List<Error> erros = Arrays.asList(new Error(msgUser, msgDev));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String msgUser = messageSource.getMessage("resource.operation.not.allowed", null, LocaleContextHolder.getLocale());
        String msgDev = ExceptionUtils.getRootCauseMessage(ex);//message root of exception
        List<Error> erros = Arrays.asList(new Error(msgUser, msgDev));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ PeopleNotExistOrNotActiveException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(PeopleNotExistOrNotActiveException ex, WebRequest request){
        String msgUser = messageSource.getMessage("people.not.exist.or.inactive", null, LocaleContextHolder.getLocale());
        String msgDev = ex.toString();//message root of exception
        List<Error> erros = Arrays.asList(new Error(msgUser, msgDev));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> createErrorList(BindingResult bindingResult){

        List<Error> errors = new ArrayList<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            String msgUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String msgDev = fieldError.toString();
            errors.add(new Error(msgUser, msgDev));
        }

        return errors;
    }

    public static class Error {

        private String messageUser;
        private String messageDeveloper;

        public Error(String messageUser, String messageDeveloper) {
            this.messageUser = messageUser;
            this.messageDeveloper = messageDeveloper;
        }

        public String getMessageUser() {
            return messageUser;
        }

        public String getMessageDeveloper() {
            return messageDeveloper;
        }

    }
}
