package com.capgemini.camel.exception.rest;

import com.capgemini.camel.exception.core.IrrecoverableException;

/**
 * Exception thrown when an operation is performed which does not conform to the REST protocol.
 *
 * @author Abbas Attarwala
 * @author Andrew Harmel-Law
 */
public class RestProtocolException extends IrrecoverableException {

    /**
     * Constructor with errorScenario arg
     * 
     * @param errorScenario 
     * @throws InstantiationException
     */
    public RestProtocolException(ErrorScenario errorScenario) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario));
    }

    /**
     * Constructor with errorScenario and cause args
     *
     * @param errorScenario
     * @param cause
     * @throws InstantiationException
     */
    public RestProtocolException(ErrorScenario errorScenario, Throwable cause) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario), cause);
    }
}
