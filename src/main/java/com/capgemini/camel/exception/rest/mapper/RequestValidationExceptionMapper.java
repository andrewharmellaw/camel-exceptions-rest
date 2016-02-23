package com.capgemini.camel.exception.rest.mapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.cxf.validation.ResponseConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception mapper class for REST API validation failures
 * 
 * @author Abbas Attarwala
 * @author Ganga Aloori
 * @author Nick Walter
 */
@Provider
public class RequestValidationExceptionMapper implements ExceptionMapper<RuntimeException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidationExceptionMapper.class);
    public static final String ERROR_LOG_MESSAGE = "{} has occurred. Exception details: {}";

    /**
     * Returns a Response in the event of a validation error.
     *
     * @param exception
     * @return the validation error as response
     */
    @Override
    public Response toResponse(RuntimeException exception) {

        if (exception instanceof ConstraintViolationException) {
            final ConstraintViolationException constraint = (ConstraintViolationException) exception;
            final boolean isResponseException = constraint instanceof ResponseConstraintViolationException;
            final StringBuilder validationErrorMessageBuilder = new StringBuilder();
            for (final ConstraintViolation< ?> violation : constraint.getConstraintViolations()) {
                LOGGER.warn(violation.getRootBeanClass().getSimpleName()
                        + "." + violation.getPropertyPath()
                        + ": " + violation.getMessage());
                validationErrorMessageBuilder.append(violation.getMessage()).append("\n");
            }
            if (isResponseException) {
                LOGGER.error(ERROR_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type("text/plain").build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationErrorMessageBuilder.toString()).type("text/plain").build();

        } else if (exception instanceof IllegalArgumentException) {
            LOGGER.error(ERROR_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid values provided for parameters.").type("text/plain").build();

        } else if (exception instanceof BadRequestException) {
            LOGGER.error(ERROR_LOG_MESSAGE, exception.getClass().getSimpleName(), exception);
            final String detailMessage = exception.getCause().getMessage();
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid request body, " + detailMessage).type("text/plain").build();

        } else if (exception instanceof ClientErrorException) {
            LOGGER.error(ERROR_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").type("text/plain").build();

        } else if (exception instanceof InternalServerErrorException) {
            return handleInternalServerErrors(exception);
        } else {
            LOGGER.warn("Unknown ValidationException occurred. "
                    + "Exception details: {}", exception.getMessage());
            LOGGER.warn(exception.getClass().getCanonicalName());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type("text/plain").build();
        }
    }


    private Response handleInternalServerErrors(RuntimeException exception) {
        Throwable cause = exception.getCause();
        if (cause == null) {
            LOGGER.error(ERROR_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid request, " + exception.getMessage())
                    .type("text/plain")
                    .build();
        }

        if (cause instanceof InvalidFormatException) {
            LOGGER.error(ERROR_LOG_MESSAGE, cause.getClass().getSimpleName(), cause.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid json payload. One or more of the JSON elements contain values that do not conform to their expected datatype(s).")
                    .type("text/plain")
                    .build();
        }

        if (cause instanceof UnrecognizedPropertyException) {
            LOGGER.error(ERROR_LOG_MESSAGE, cause.getClass().getSimpleName(), cause.getMessage());
            UnrecognizedPropertyException ure = (UnrecognizedPropertyException) cause;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The element " + ure.getPropertyName() + " is not expected within the JSON request. Please make sure your JSON conforms to the request schema.")
                    .type("text/plain")
                    .build();
        }

        if (cause instanceof JsonParseException) {
            LOGGER.error(ERROR_LOG_MESSAGE, cause.getClass().getSimpleName(), cause.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid json payload. One or more of the JSON elements contain values that do not conform to their expected datatype(s). ")
                    .type("text/plain")
                    .build();
        }

        Throwable rootCause = cause.getCause();
        if (rootCause != null && rootCause instanceof NumberFormatException) {
            LOGGER.error(ERROR_LOG_MESSAGE, cause.getClass().getSimpleName(), cause.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid json payload. One or more of the numeric JSON elements contain invalid value(s).")
                    .type("text/plain")
                    .build();
        }

        LOGGER.error(ERROR_LOG_MESSAGE, cause.getClass().getSimpleName(), cause.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("The request was inappropriate.")
                .type("text/plain")
                .build();
    }
}