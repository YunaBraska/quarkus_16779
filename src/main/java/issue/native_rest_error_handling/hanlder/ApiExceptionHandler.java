package issue.native_rest_error_handling.hanlder;

import issue.native_rest_error_handling.model.ApiError;
import issue.native_rest_error_handling.model.ApiException;
import org.jboss.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
//https://howtodoinjava.com/resteasy/resteasy-exceptionmapper-example/
public class ApiExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(ApiExceptionHandler.class);

    @Override
    public Response toResponse(final Exception exception) {

        if (exception instanceof ApiException) {
            LOG.warn("DebugError [" + exception.toString() + "]");
            final ApiError body = ((ApiException) exception).toApiError();
            return Response.status(body.getStatus()).entity(body).build();
        }
        LOG.error("DebugError", exception);
        return Response.status(INTERNAL_SERVER_ERROR).entity("GENERIC ERROR").build();
    }
}