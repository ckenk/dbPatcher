package com.fca.dbedit.exception

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by kkulathilake on 5/20/2016.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorResource {
    String code;
    String message;

    public ErrorResource() { }

    public ErrorResource(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
