package com.example.Spring.Boot.Blog.exception;

import com.example.Spring.Boot.Blog.dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //Handle Specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPiException(BlogApiException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    //when a user tries to create,update,delete post for which he isnot authorized
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentialsExceptionException(BadCredentialsException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }

    //handling exception when data passed from json body violates our constraint
    //ist approach by extending ResponseEntityExceptionHandler and overiding handleMethodARgumentNotValid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( (error)->{
            String fieldName=((FieldError)error).getField();
            String fieldMessegae=error.getDefaultMessage();
            errors.put(fieldName,fieldMessegae);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    //2nd Approach by writing custome exception method and handling spcific exception
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest webRequest){
//        Map<String,String> errorsMap=new HashMap<>();
//        exception.getBindingResult().getAllErrors().forEach( (error)->{
//            String fieldName=((FieldError)error).getField();
//            String fieldMessegae=error.getDefaultMessage();
//            errorsMap.put(fieldName,fieldMessegae);
//        });
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap);
//    }

    //handle Global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

}
