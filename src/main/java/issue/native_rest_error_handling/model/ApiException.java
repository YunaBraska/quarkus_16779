package issue.native_rest_error_handling.model;

import java.util.Objects;

import static javax.ws.rs.core.Response.Status;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class ApiException extends RuntimeException {
    private Status status = INTERNAL_SERVER_ERROR;
    private String message = "generic_error";

    public static ApiException unknownUserError(final Long userId) {
        return new ApiException().setStatus(NOT_FOUND).setMessage("Unknown user id [" + userId + "]");
    }

    public ApiError toApiError(){
        return new ApiError().setStatus(status).setMessage(message);
    }

    public Status getStatus() {
        return status;
    }

    public ApiException setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ApiException setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiException that = (ApiException) o;
        return status == that.status && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
