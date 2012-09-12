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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class Vote extends AbstractModel implements SocialActivity
{
    public enum VoteType { UP, DOWN }

    @OneToOne(optional = false)
    public Module playModule;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public VoteType voteType;

    @Column(nullable = false)
    public Date lastChangeDate;

    public boolean isUpVote()
    {
        return voteType == VoteType.UP;
    }

    @Override
    public String getDescription()
    {
        return String.format("%s-voted %s",
                             isUpVote() ? "up" : "down",
                             playModule.name);
    }

    @Override
    public Date getDate()
    {
        return lastChangeDate;
    }

    @Override
    public String getType()
    {
        return isUpVote() ? "upVote" : "downVote";
    }
}
