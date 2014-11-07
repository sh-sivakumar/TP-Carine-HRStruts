/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.exception;

/**
 *
 * @author franckmazzolo
 */
public class ServiceIndisponibleException extends Exception {

    public ServiceIndisponibleException() {
    }

    public ServiceIndisponibleException(String message) {
        super(message);
    }

    public ServiceIndisponibleException(Throwable cause) {
        super(cause);
    }

    public ServiceIndisponibleException(String message, Throwable cause) {
        super(message, cause);
    }

}
