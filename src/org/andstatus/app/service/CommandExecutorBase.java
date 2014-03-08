/* 
 * Copyright (c) 2014 yvolk (Yuri Volkov), http://yurivolkov.com
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

package org.andstatus.app.service;

import org.andstatus.app.account.MyAccount;
import org.andstatus.app.data.TimelineTypeEnum;
import org.andstatus.app.net.ConnectionException;
import org.andstatus.app.util.MyLog;

abstract class CommandExecutorBase implements CommandExecutorStrategy, CommandExecutorParent {
    protected CommandExecutionContext execContext = null;
    private CommandExecutorParent parent = null;

    protected static CommandExecutorStrategy getStrategy(CommandData commandData, CommandExecutorParent parent) {
        return getStrategy(new CommandExecutionContext(commandData, commandData.getAccount()))
                .setParent(parent);
    }

    protected static CommandExecutorStrategy getStrategy(CommandExecutionContext execContext) {
        CommandExecutorStrategy strategy;
        if (execContext.getMyAccount() == null) {
            if (execContext.getTimelineType() == TimelineTypeEnum.PUBLIC) {
                strategy = new CommandExecutorAllOrigins();
            } else {
                strategy = new CommandExecutorAllAccounts();
            }
        } else {
            switch (execContext.getCommandData().getCommand()) {
                case AUTOMATIC_UPDATE:
                case FETCH_TIMELINE:
                    strategy = new CommandExecutorLoadTimeline();
                    break;
                case SEARCH_MESSAGE:
                    strategy = new CommandExecutorSearch();
                    break;
                default:
                    strategy = new CommandExecutorOther();
                    break;
            }
        }
        strategy.setContext(execContext);
        MyLog.d("CommandExecutorStrategy", strategy.getClass().getSimpleName() + " executing " + execContext);
        return strategy;
    }

    @Override
    public CommandExecutorStrategy setContext(CommandExecutionContext execContext) {
        this.execContext = execContext;
        return this;
    }

    protected CommandExecutorBase() {
    }
    
    public static CommandExecutorBase newInstance(Class<? extends CommandExecutorBase> clazz, CommandExecutionContext execContextIn) {
        CommandExecutorBase exec = null;
        try {
            exec = clazz.newInstance();
            if (execContextIn == null) {
                exec.execContext = new CommandExecutionContext(CommandData.getEmpty(), null);
            } else {
                exec.execContext = execContextIn;
            }
        } catch (InstantiationException e) {
            MyLog.e(CommandExecutorBase.class, "class=" + clazz, e);
        } catch (IllegalAccessException e) {
            MyLog.e(CommandExecutorBase.class, "class=" + clazz, e);
        }
        return exec;
    }

    @Override
    public CommandExecutorStrategy setMyAccount(MyAccount ma) {
        execContext.setMyAccount(ma);
        return this;
    }
    
    @Override
    public CommandExecutorStrategy setParent(CommandExecutorParent parent) {
        this.parent = parent;
        return this;
    }
    
    @Override
    public boolean isStopping() {
        if (parent != null) {
            return parent.isStopping();
        } else {
            return false;
        }
    }

    protected void logConnectionException(ConnectionException e, String detailedMessage) {
        if (e.isHardError()) {
            execContext.getResult().incrementParseExceptions();
        } else {
            execContext.getResult().incrementNumIoExceptions();
        }
        MyLog.e(this, detailedMessage + ": " + e.toString());
    }
}