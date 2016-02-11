package com.capgemini.camel.exception.rest;

import com.capgemini.camel.exception.core.RecoverableException;

/**
 * Exception thrown when a REST client has issues making an HTTP connection to a REST resource
 *
 * @author Abbas Attarwala
 * @author Andrew Harmel-Law
 */
public class RestConnectionException extends RecoverableException {

    /**
     * Constructor with errorScenario arg
     * 
     * @param errorScenario 
     * @throws InstantiationException
     */
    public RestConnectionException(ErrorScenario errorScenario) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario));
    }

    /**
     * Constructor with errorScenario and cause arg
     *
     * @param errorScenario
     * @param cause
     * @throws InstantiationException
     */
    public RestConnectionException(ErrorScenario errorScenario, Throwable cause) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario), cause);
    }

}
