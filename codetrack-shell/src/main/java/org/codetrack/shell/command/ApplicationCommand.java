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

package org.codetrack.shell.command;

import com.google.common.base.Strings;
import org.codetrack.annotation.identify.Product;
import org.codetrack.shell.ShellApplicationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Display application information in shell screen
 *
 * @author josecmoj at 25/04/15.
 */
@Product(id = "codetrack-shell")
@Component
public class ApplicationCommand implements CommandMarker {

    /**
     * Inject ShellApplicationInfo instance
     */
    @Autowired
    ShellApplicationInfo shellApplicationInfo;

    /**
     * Indicate info command availability
     *
     * @return true
     */
    @CliAvailabilityIndicator({"info"})
    public boolean isInformationAvailable() {
        return true;
    }

    /**
     * Command to display application information
     *
     * @param version   to display application version
     * @param name      to display application name
     * @param license   to display application license
     * @param copyright to display application copyright
     * @return String display information
     */
    @CliCommand(value = "info")
    public String info(
            @CliOption(key = "version", mandatory = false, specifiedDefaultValue = "", help = "Display tool version")
            String version,
            @CliOption(key = "name", mandatory = false, specifiedDefaultValue = "", help = "Display tool name")
            String name,
            @CliOption(key = "license", mandatory = false, specifiedDefaultValue = "", help = "Display tool license info")
            String license,
            @CliOption(key = "copyright", mandatory = false, specifiedDefaultValue = "", help = "Display tool copyright info ")
            String copyright
    ) {

        if (!Strings.isNullOrEmpty(version)) {
            return shellApplicationInfo.getVersion();
        }

        if (!Strings.isNullOrEmpty(name)) {
            return shellApplicationInfo.getName();
        }

        if (!Strings.isNullOrEmpty(license)) {
            return shellApplicationInfo.getLicense();
        }

        if (!Strings.isNullOrEmpty(copyright)) {
            return shellApplicationInfo.getCopyright();
        }

        return shellApplicationInfo.getName() + " - " + shellApplicationInfo.getVersion();

    }
}
