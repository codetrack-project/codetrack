/*
 *  Copyright 2015 the original author or authors members of codetrack.org
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.codetrack.shell;

import org.codetrack.annotation.identify.Product;
import org.codetrack.annotation.identify.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author josecmoj at 25/04/15.
 */
@Product(id = "codetrack-shell")
@Reference(id = "shell.properties")
@Configuration
@PropertySource("classpath:META-INF/codetrack/shell.properties")
public class ShellConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public ShellApplicationInfo shellApplicationInfo() {

        return ShellApplicationInfo.newBuilder()
                .name(env.getProperty("shell.application.info.name"))
                .version(env.getProperty("shell.application.info.version"))
                .welcome(env.getProperty("shell.application.info.welcome"))
                .copyright(env.getProperty("shell.application.info.copyright"))
                .license(env.getProperty("shell.application.info.license"))
                .prompt(env.getProperty("shell.application.info.prompt"))
                .build();

    }

    @Bean
    public ShellFileInfo shellFileInfo() {
        return new ShellFileInfo(env.getProperty("shell.files.history"));
    }
}
