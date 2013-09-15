/* 
 * Copyright (c) 2013 yvolk (Yuri Volkov), http://yurivolkov.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.andstatus.app.net;

import java.util.ArrayList;
import java.util.List;

public class ConnectionEmpty extends Connection {

    @Override
    protected String getApiPath1(ApiRoutineEnum routine) {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public MbRateLimitStatus rateLimitStatus() throws ConnectionException {
        // TODO Auto-generated method stub
        return new MbRateLimitStatus();
    }

    @Override
    public MbUser verifyCredentials() throws ConnectionException {
        // TODO Auto-generated method stub
        return MbUser.getEmpty();
    }

    @Override
    public MbMessage destroyFavorite(String statusId) throws ConnectionException {
        // TODO Auto-generated method stub
        return MbMessage.getEmpty();
    }

    @Override
    public MbMessage createFavorite(String statusId) throws ConnectionException {
        // TODO Auto-generated method stub
        return MbMessage.getEmpty();
    }

    @Override
    public boolean destroyStatus(String statusId) throws ConnectionException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<String> getIdsOfUsersFollowedBy(String userId) throws ConnectionException {
        // TODO Auto-generated method stub
        return new ArrayList<String>();
    }

    @Override
    public MbMessage getMessage(String statusId) throws ConnectionException {
        // TODO Auto-generated method stub
        return MbMessage.getEmpty();
    }

    @Override
    public MbMessage updateStatus(String message, String inReplyToId) throws ConnectionException {
        // TODO Auto-generated method stub
        return MbMessage.getEmpty();
    }

    @Override
    public MbMessage postDirectMessage(String message, String userId) throws ConnectionException {
        // TODO Auto-generated method stub
        return MbMessage.getEmpty();
    }

    @Override
    public MbMessage postReblog(String rebloggedId) throws ConnectionException {
        // TODO Auto-generated method stub
        return MbMessage.getEmpty();
    }

    @Override
    public List<MbMessage> getTimeline(ApiRoutineEnum apiRoutine, TimelinePosition sinceId,
            int limit, String userId) throws ConnectionException {
        // TODO Auto-generated method stub
        return new ArrayList<MbMessage>();
    }

    @Override
    public MbUser followUser(String userId, Boolean follow) throws ConnectionException {
        // TODO Auto-generated method stub
        return MbUser.getEmpty();
    }

    @Override
    public MbUser getUser(String userId) throws ConnectionException {
        // TODO Auto-generated method stub
        return MbUser.getEmpty();
    }

}
