package com.capgemini.camel.exception.rest;

import com.capgemini.camel.exception.core.RecoverableException;

/**
 * Exception occurring when trying to read JSON resulting from a REST resource call.
 *
 * @author Abbas Attarwala
 * @author Andrew Harmel-Law
 */
public class JsonReadException extends RecoverableException {

    /**
     * Constructor with message arg
     * 
     * @param errorScenario 
     * @throws InstantiationException
     */
    public JsonReadException(ErrorScenario errorScenario) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario));
    }

    /**
     * Constructor with message and cause arg
     *
     * @param errorScenario
     * @param cause
     * @throws InstantiationException
     */
    public JsonReadException(ErrorScenario errorScenario, Throwable cause) throws InstantiationException {
        super(ErrorScenario.generateMessage(errorScenario), cause);
    }
}
