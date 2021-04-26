package issue.native_rest_error_handling.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static javax.ws.rs.core.Response.Status;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class ApiError {

    @JsonProperty("status")
    private Status status = INTERNAL_SERVER_ERROR;
    @JsonProperty("message")
    private String message = "generic_error";

    public static ApiError unknownUserError(final Long userId) {
        return new ApiError().setStatus(NOT_FOUND).setMessage("Unknown user id [" + userId + "]");
    }

    public Status getStatus() {
        return status;
    }

    public ApiError setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiError setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiError that = (ApiError) o;
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
