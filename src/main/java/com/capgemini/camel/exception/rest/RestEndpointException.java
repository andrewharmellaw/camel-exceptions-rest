package com.capgemini.camel.exception.rest;

import com.capgemini.camel.exception.core.RecoverableException;

/**
 * Exception thrown when there is an exception thrown by the RESTful Endpoint.
 *
 * @author Abbas Attarwala
 * @author Andrew Harmel-Law
 */
public class RestEndpointException extends RecoverableException {

    /**
     * Constructor with message arg
     * 
     * @param errorScenario 
     * @throws InstantiationException
     */
    public RestEndpointException(ErrorScenario errorScenario) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario));
    }

    /**
     * Constructor with message and cause args
     *
     * @param errorScenario
     * @param cause
     * @throws InstantiationException
     */
    public RestEndpointException(ErrorScenario errorScenario, Throwable cause) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario), cause);
    }
}
