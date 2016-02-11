package com.capgemini.camel.exception.rest;

import java.text.MessageFormat;

/**
 * An enum containing the list of Error Scenarios that could occur during REST communications.
 *
 * Each {@link com.capgemini.camel.exception.rest.ErrorScenario} contains a log message to log details related to the error scenario
 * and a response message that is propagated back with limited information.
 *
 * @author Abbas Attarwala
 */
public enum ErrorScenario {
    
    CB_TIMED_OUT("The Circuit Breaker timed out whilst executing the {0} resource call.", "A back-end service call failed"),
    CB_SHORT_CIRCUITED("The {0} resource endpoint was found to be short circuited.", "A back-end service call failed"),
    CB_REJECTED_THREAD_EXECUTION("Circuit Breaker rejected thread execution for {0}", "A back-end service call failed"),
    CB_REJECTED_SEMAPHORE_FALLBACK("Circuit Breaker rejected the Semaphore Fallback for {0}", "A back-end service call failed"),
    CB_REJECTED_SEMAPHORE_EXECUTION("The request was denied for the {0} resource.", "A back-end service call failed"),
    CB_UNKNOWN_ERROR("An unknown error occured whilst calling the {0} resource. Error details are: {1}", "A back-end service call failed"),
    CB_BAD_REQUEST("A bad request was issued by the REST client while calling the {0} resource. Request details are: {1}", "A back-end service call failed"),
    URI_CREATION_FAILED("Unable to create URI --> {0}. Error details: {1}", "A back-end service call failed"),
    NULL_REQUEST_PAYLOAD("Payload object found NULL for the {0} method while calling the {1} resource.", "A back-end service call failed"),
    EMPTY_REQUEST_PAYLOAD("Sending an empty payload using the {0} method while calling the {1} resource.", ""),
    NULL_HTTP_RESPONSE("The HTTP response from the {0} resource was found to be NULL.", "A back-end service call failed"),
    REST_EMPTY_RESPONSE("Empty response from the {0} resource. Response details are: {1}", "A back-end service call failed"),
    JSON_READ_FAILED("Unable to read JSON response from the recieved {0} resource. Response details are: {1}", "A back-end service call failed"),
    RESPONSE_FAILURE("The {0} resource returned an HTTP Status Code: {1} with the response payload: {2}", "A back-end service call failed"),
    CONFLICT_HTTP_RESPONSE("The response from the {0} returned Status Code: {1} with response payload{2}. Resource was a conflict","back-end service returned a conflict response");

    private final String logMessage;
    private final String responseMessage;

    /**
     * Returns the formatted log message related to the error scenario.
     * 
     * @param args
     * @return 
     */
    public String getLogMessage(Object... args) {
        return MessageFormat.format(logMessage, args);
    }
    
    /**
     * Returns the log message related to the error scenario
     * 
     * @return 
     */
    public String getLogMessage() {
        return logMessage;
    }
    
    /**
     * Returns the formatted responseMessage related to the error scenario
     * 
     * @param args
     * @return 
     */
    public String getResponseMessage(Object... args) {
        return MessageFormat.format(responseMessage, args);
    }
    
    /**
     * Returns the responseMessage related to the error scenario.
     * 
     * @return 
     */
    public String getResponseMessage() {
        return responseMessage;
    }
    
    /**
     * The private constructor
     */
    private ErrorScenario(String logMessage, String responseMessage) {
        this.logMessage = logMessage;
        this.responseMessage = responseMessage;
    }

    /**
     * This static method returns the provided string with ". " prepended, unless it is null or empty in which case it
     * returns ""
     *
     * @param responsePayload
     * @return
     */
    protected static String emptySafeString(String responsePayload) {
        String emptySafeResponsePayload;

        if (responsePayload != null && !responsePayload.equals("")) {
            emptySafeResponsePayload = ". " + responsePayload;
        } else {
            emptySafeResponsePayload = "";
        }

        return emptySafeResponsePayload;
    }

    /**
     * This static method returns the response message related to the scenario provided
     * {@link ErrorScenario}
     */
    protected static String generateMessage(ErrorScenario errorScenario) throws InstantiationException {
        if (errorScenario != null) {
            return errorScenario.getResponseMessage();
        }
        throw new InstantiationException("Cannot instantiate an exception with a NULL error scenario.");
    }
}
