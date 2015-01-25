package controllers;

import com.google.inject.Inject;
import models.User;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.Router;
import ninja.session.FlashScope;
import ninja.session.Session;
import services.AuthenticationService;
import services.PlayGameService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Channel on 2015-01-16.
 */
public class AuthenticationController {
    @Inject
    AuthenticationService authenticationService;

    @Inject
    Router router;

    @Inject
    private PlayGameService playGameService;

    int numberPlayers = 4;

    public Result login(FlashScope flashScope, Context context) {
        Result result = Results.html();
        String httpMethod = context.getMethod();
        switch (httpMethod) {
            case "GET":
                return Results.html();

            case "POST":
                String username = context.getParameter("login");
                String password = context.getParameter("password");

                if (authenticationService.authenticate(username, password)) {
                    context.getSession().put("login", username);
                    return result.html().redirect("/index");
                } else {
                    context.getSession().clear();
                    flashScope.error("Login Failed");
                }
        }
        return result;
    }

    public Result register(FlashScope flashScope, Context context) {
        Result result = Results.html();
        String httpMethod = context.getMethod();
        switch (httpMethod) {
            case "GET":
                return Results.html();

            case "POST":
                List<User> users = playGameService.getPlayers();
                if(users.isEmpty()){
                    authenticationService.register("comp1","cpu");
                    authenticationService.register("comp2","cpu");
                    authenticationService.register("comp3","cpu");
                    authenticationService.register("comp4","cpu");
                }
                String username = context.getParameter("login");
                String password = context.getParameter("password");

                if (!username.equals("") && username != null && !password.equals("") && password != null) {
                    if (authenticationService.usernameInUse(username))                         {
                        flashScope.error("Username already exists");
                    } else {

                        if (authenticationService.register(username, password)) {
                            context.getSession().put("login", username);

                            return result.html().redirect("/index");
                        }
                    }
                }
        }
        return result;
    }


    public Result logout(Context context) {
        context.getSession().clear();
        return Results.html().redirect(router.getReverseRoute(ApplicationController.class, "index"));
    }
}
