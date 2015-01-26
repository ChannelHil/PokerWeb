/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;


import controllers.AuthenticationController;
import controllers.HistoryController;
import controllers.UserController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
        router.GET().route("/index").with(ApplicationController.class, "index");
        router.GET().route("/login").with(AuthenticationController.class, "login");
        router.POST().route("/login").with(AuthenticationController.class, "login");
        router.GET().route("/register").with(AuthenticationController.class, "register");
        router.POST().route("/register").with(AuthenticationController.class, "register");
        router.GET().route("/user/{name}").with(UserController.class, "exists");
        router.GET().route("/history").with(HistoryController.class, "history");
        router.GET().route("/logout").with(AuthenticationController.class, "logout");
        router.GET().route("/play").with(ApplicationController.class, "play");
        router.POST().route("/host").with(ApplicationController.class, "host");
        router.POST().route("/start").with(ApplicationController.class, "start");
        router.POST().route("/view").with(ApplicationController.class, "view");
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
       router.GET().route("/.*").with(ApplicationController.class, "index");
        //router.GET().route("/.*").with(AuthenticationController.class, "login");
    }

}
