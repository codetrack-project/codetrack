
/*
 * Copyright 2015 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.codetrack.shell.information;

import org.codetrack.shell.ShellFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultHistoryFileNameProvider;
import org.springframework.stereotype.Component;

/**
 * Provides the History file name
 *
 * @author josecmoj at 20/04/15.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HistoryFileNameProvider extends DefaultHistoryFileNameProvider{

    @Autowired
    private ShellFileInfo shellFileInfo;

    /**
     * Access history file name. Is generated on the running folder
     *
     * @return String
     */
    @Override
    public String getHistoryFileName() {
        return shellFileInfo.getHistoryFileName();
    }

    /**
     * Access provider name for the history file
     *
     * @return String name of provider name
     */
    @Override
    public String getProviderName() {
        return "codetrack history provider";
    }
}
