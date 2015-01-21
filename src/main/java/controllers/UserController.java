package controllers;

import com.google.inject.Inject;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import services.AuthenticationService;

/**
 * Created by Channel on 2015-01-16.
 */
public class UserController {
    @Inject
    AuthenticationService authenticationService;

    public Result exists(@PathParam("name") String username){
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.exists = authenticationService.usernameInUse(username);

        return Results.json().render(simplePojo);
    }
    public static class SimplePojo {

        public boolean exists;

    }
}
