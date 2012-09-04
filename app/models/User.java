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
package models;

import be.objectify.deadbolt.models.Permission;
import be.objectify.deadbolt.models.Role;
import be.objectify.deadbolt.models.RoleHolder;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
@Table(name = "MPO_USER")
public class User extends AbstractModel implements RoleHolder
{
    @Column(nullable = false, length = 40, unique = true)
    public String userName;

    @Column(nullable = false, length = 100)
    public String displayName;

    @Column(nullable = true, length = 2500)
    public String avatarUrl;

    @Column(nullable = false)
    public Boolean accountActive;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    public List<UserRole> roles;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Rate> rates;

    public static final Finder<Long, User> FIND = new Finder<Long, User>(Long.class,
            User.class);

    public static int count() {
        return FIND.findRowCount();
    }

    @Override
    public List<? extends Role> getRoles()
    {
        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions()
    {
        // for now, let's go with role-based security.  I don't think we need any fine-grained control.
        return Collections.emptyList();
    }
}
