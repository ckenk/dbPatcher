package com.fca.dbedit.exception

import groovy.json.JsonBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.util.WebUtils

import java.lang.invoke.MethodHandles
import java.sql.SQLException

/**
 * Created by kkulathilake on 5/20/2016.
 */
@ControllerAdvice
class DbEditExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

//    http://stackoverflow.com/questions/22157687/spring-mvc-rest-handing-bad-url-404-by-returning-json
    @ExceptionHandler(value = [EngVehicleConfigUpdateFailedException.class, SQLException.class, IOException.class])
    public ResponseEntity<String> exception(Exception e, WebRequest request) {
        EngVehicleConfigUpdateFailedException evcCongUpFailedException = (EngVehicleConfigUpdateFailedException) e;
        ErrorResource error = new ErrorResource(
                (evcCongUpFailedException.status == null ? HttpStatus.INTERNAL_SERVER_ERROR.toString() : evcCongUpFailedException.status),
                evcCongUpFailedException.message);
        String s = new JsonBuilder( error).toPrettyString()
        LOGGER.info(s)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        LOGGER.error((e.message != null ? e.message : "<no message error>"),e)

        return new ResponseEntity<String>(error, headers, HttpStatus.BAD_REQUEST);
    }
}
