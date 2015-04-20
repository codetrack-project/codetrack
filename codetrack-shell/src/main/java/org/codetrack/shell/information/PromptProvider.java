
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

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

/**
 * Provides prompt format
 *
 * @author josecmoj at 20/04/15.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PromptProvider extends DefaultPromptProvider {

    /**
     * Access to prompt text
     *
     * @return String prompt text
     */
    @Override
    public String getPrompt() {
        return "codetrack>";
    }

    /**
     * Access to provider name
     *
     * @return String provider name
     */
    @Override
    public String getProviderName() {
        return "Codetrack prompt provider";
    }
}
