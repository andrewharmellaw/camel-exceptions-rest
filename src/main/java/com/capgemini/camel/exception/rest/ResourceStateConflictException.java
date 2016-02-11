package com.capgemini.camel.exception.rest;

import com.capgemini.camel.exception.core.IrrecoverableException;

/**
 * Exception thrown when an HTTP response code is 409
 *
 * @author Biplab Nayak
 * @author Andrew Harmel-Law
 */
public class ResourceStateConflictException extends IrrecoverableException {

    /**
     * Constructor with errorScenario arg
     *
     * @param errorScenario
     * @throws InstantiationException
     */
    public ResourceStateConflictException(ErrorScenario errorScenario) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario));
    }

    /**
     * Constructor with errorScenario and cause args.
     *
     * @param errorScenario
     * @param cause
     * @throws InstantiationException
     */
    public ResourceStateConflictException(ErrorScenario errorScenario, Throwable cause) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario), cause);
    }

    /**
     * Constructor with errorScenario arg
     *
     * @param errorScenario
     * @param responsePayload the message that was returned from the service call
     * @throws InstantiationException
     */
    public ResourceStateConflictException(ErrorScenario errorScenario, String responsePayload) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario) + (ErrorScenario.emptySafeString(responsePayload)));
    }

    /**
     * Constructor with errorScenario arg
     *
     * @param responsePayload the message that was returned from the service call
     * @throws InstantiationException
     */
    public ResourceStateConflictException(String responsePayload) throws InstantiationException {
        super(responsePayload);
    }

}
