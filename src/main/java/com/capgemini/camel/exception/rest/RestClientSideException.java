package com.capgemini.camel.exception.rest;

import com.capgemini.camel.exception.core.IrrecoverableException;

/**
 * Exception thrown when a REST client generates an Irrecoverable Exception.
 *
 * @author Abbas Attarwala
 * @author Andrew Harmel-Law
 */
public class RestClientSideException extends IrrecoverableException {

    /**
     * Constructor with errorScenario arg
     * 
     * @param errorScenario 
     * @throws InstantiationException
     */
    public RestClientSideException(ErrorScenario errorScenario) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario));
    }

    /**
     * Constructor with errorScenario and cause args.
     *
     * @param errorScenario
     * @param cause
     * @throws InstantiationException
     */
    public RestClientSideException(ErrorScenario errorScenario, Throwable cause) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario), cause);
    }

    /**
     * Constructor with errorScenario arg
     *
     * @param errorScenario
     * @param responsePayload the message that was returned from the service call
     * @throws InstantiationException
     */
    public RestClientSideException(ErrorScenario errorScenario, String responsePayload) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario) + (ErrorScenario.emptySafeString(responsePayload)));
    }

    /**
     * Constructor with errorScenario arg
     *
     * @param responsePayload the message that was returned from the service call
     * @throws InstantiationException
     */
    public RestClientSideException(String responsePayload) throws InstantiationException {
        super(responsePayload);
    }
}
