package filters;


import com.google.inject.Inject;
import controllers.ApplicationController;
import controllers.AuthenticationController;
import ninja.*;

/**
 * Created by Channel on 2015-01-16.
 */
public class SecureFilter implements Filter {
    public static String USERNAME = "login";

    @Inject
    Router router;

    @Override
    public Result filter(FilterChain filterChain, Context context) {

        Result result = Results.html();
        if (context.getSession() == null || context.getSession().get(USERNAME) == null) {
           //return result.html().redirect("/login");
            return Results.redirect(router.getReverseRoute(AuthenticationController.class, "login"));

        } else {
            return filterChain.next(context);
        }
    }

}
