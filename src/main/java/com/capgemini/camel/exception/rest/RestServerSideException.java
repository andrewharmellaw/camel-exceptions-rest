package com.capgemini.camel.exception.rest;

import com.capgemini.camel.exception.core.IrrecoverableException;

/**
 * Exception thrown when an internal exception is returned by a REST resource.
 *
 * @author Abbas Attarwala
 * @author Andrew Harmel-Law
 */
public class RestServerSideException extends IrrecoverableException {

    /**
     * Constructor with errorScenario arg
     * 
     * @param errorScenario 
     * @throws InstantiationException
     */
    public RestServerSideException(ErrorScenario errorScenario) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario));
    }

    /**
     * Constructor with errorScenario arg
     *
     * @param errorScenario
     * @param responsePayload the message that was returned from the service call
     * @throws InstantiationException
     */
    public RestServerSideException(ErrorScenario errorScenario, String responsePayload) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario) + (ErrorScenario.emptySafeString(responsePayload)));
    }

    /**
     * Constructor with errorScenario and cause args
     *
     * @param errorScenario
     * @param cause
     * @throws InstantiationException
     */
    public RestServerSideException(ErrorScenario errorScenario, Throwable cause) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario), cause);
    }

}
