/*
 * Copyright (C) 2013 yvolk (Yuri Volkov), http://yurivolkov.com
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

import org.andstatus.app.origin.Origin;

/**
 * 'Mb' stands for "Microblogging system" 
 * @author yvolk@yurivolkov.com
 */
public class MbConfig {
    private boolean isEmpty = true;
    
    public int shortUrlLength = 0;
    public int textLimit = 0;
    
    public static MbConfig getEmpty() {
        MbConfig config = new MbConfig();
        return config;
    }

    public static MbConfig fromTextLimit(int textLimit) {
        MbConfig config = new MbConfig();
        config.textLimit = textLimit;
        config.isEmpty = (textLimit == 0);
        return config;
    }
    
    private MbConfig() {}
    
    public boolean isEmpty() {
        return (isEmpty);
    }
}
