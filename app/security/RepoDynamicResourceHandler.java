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
package security;

import be.objectify.deadbolt.AbstractDynamicResourceHandler;
import be.objectify.deadbolt.DeadboltHandler;
import be.objectify.deadbolt.DynamicResourceHandler;
import play.mvc.Http;
import security.dynamic.AllowToVote;

/**
 * @author Steve Chaloner
 */
public class RepoDynamicResourceHandler extends AbstractDynamicResourceHandler
{
    @Override
    public boolean isAllowed(String name,
                             String meta,
                             DeadboltHandler deadboltHandler,
                             Http.Context context)
    {
        boolean allowed = false;
        DynamicResourceHandler handler = null;
        if (AllowToVote.KEY.equals(name))
        {
            handler = new AllowToVote();
        }

        if (handler != null)
        {
            allowed = handler.isAllowed(name,
                                        meta,
                                        deadboltHandler,
                                        context);
        }

        return allowed;
    }
}
