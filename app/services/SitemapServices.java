/*
 * Copyright 2012 The Play! Framework
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
package services;

import controllers.routes;
import models.Module;
import models.memory.Sitemap;
import play.api.mvc.RequestHeader;
import play.mvc.Http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pere Villega (pere.villega@gmail.com)
 */
public class SitemapServices {

    public static final String SITEMAP_CACHE_KEY = "siteMap";

    /**
     * Generates the sitemaps entries for the application.
     * Unfortunately this has to be generated "by hand", if we want to display more entries we have to add them to the list ourselves
     *
     * @param request the current request
     * @return a list of sitemap entries
     */
    public static List<Sitemap> generateSitemap(Http.Request request){
        List<Sitemap> list = new ArrayList<Sitemap>();

        // home
        list.add(new Sitemap(routes.Application.index().absoluteURL(request)));

        // users
        list.add(new Sitemap(routes.Application.listUsers().absoluteURL(request), "daily", "0.5" ));

        // modules lists
        list.add(new Sitemap(routes.Modules.getModulesByPlayVersion("1").absoluteURL(request), "hourly", "0.9" ));
        list.add(new Sitemap(routes.Modules.getModulesByPlayVersion("2").absoluteURL(request), "hourly", "0.9" ));

        // latest modules
        list.add(new Sitemap(routes.Modules.getLatestModulesByPlayVersion("1").absoluteURL(request), "hourly", "0.8" ));
        list.add(new Sitemap(routes.Modules.getLatestModulesByPlayVersion("2").absoluteURL(request), "hourly", "0.8" ));

        // highest rated modules
        list.add(new Sitemap(routes.Modules.getHighestRatedModulesByPlayVersion("1").absoluteURL(request), "hourly", "0.8" ));
        list.add(new Sitemap(routes.Modules.getHighestRatedModulesByPlayVersion("2").absoluteURL(request), "hourly", "0.8" ));

        // featured rated modules
        list.add(new Sitemap(routes.Modules.getFeaturedModulesByPlayVersion("1").absoluteURL(request), "daily", "0.8" ));
        list.add(new Sitemap(routes.Modules.getFeaturedModulesByPlayVersion("2").absoluteURL(request), "daily", "0.8" ));

        // modules details
        List<Module> modules = Module.all();
        for(Module mod: modules) {
            list.add(new Sitemap(routes.Modules.details(mod.key).absoluteURL(request), "daily", "1" ));
        }

        return list;
    }
}
