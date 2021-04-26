package issue.native_rest_error_handling.controller;

import issue.native_rest_error_handling.model.User;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static issue.native_rest_error_handling.model.ApiException.unknownUserError;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
public class UserController {

    private final Set<User> userRepository = ConcurrentHashMap.newKeySet();

    @Inject
    public UserController() {
        userRepository.add(new User().setId(16779L).setName("Yuna"));
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public User getById(@PathParam("id") final Long userId) {
        return userRepository.stream().filter(user -> user.getId().equals(userId)).findAny().orElseThrow(() -> unknownUserError(userId));
    }
}
