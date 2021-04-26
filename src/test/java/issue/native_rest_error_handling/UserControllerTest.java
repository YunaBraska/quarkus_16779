package issue.native_rest_error_handling;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import issue.native_rest_error_handling.model.ApiError;
import issue.native_rest_error_handling.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@QuarkusTest
class UserControllerTest {

    @Test
    @DisplayName("Get user by id")
    void getPlayerById() {
        final User result = restClient()
                .get("users/" + 16779)
                .then().assertThat().statusCode(OK.getStatusCode()).extract()
                .as(new TypeRef<>() {
                });
        assertThat(result.getId(), is(notNullValue()));
        assertThat(result.getId(), is(equalTo(16779L)));
        assertThat(result.getName(), is(equalTo("Yuna")));
    }

    @Test
    @DisplayName("Get non existing user - DOES NOT WORK ON NATIVE")
    void getUserWithInvalidId() {
        long userId = 200888;
        final ApiError result = restClient()
                .get("users/" + userId)
                .then().assertThat().statusCode(NOT_FOUND.getStatusCode()).extract()
                .as(new TypeRef<>() {
                });
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatus(), is(equalTo(NOT_FOUND)));
        assertThat(result.getMessage(), is(equalTo("Unknown user id [" + userId + "]")));
    }

    public RequestSpecification restClient() {
        return given().log().all()
                .contentType(JSON)
                .filter(new ResponseLoggingFilter())
                ;
    }

}