
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

package org.codetrack.shell.information;

import org.codetrack.annotation.identify.Product;
import org.codetrack.shell.ShellApplicationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.stereotype.Component;

/**
 * Provides the application banner ASCII art
 *
 * @author josecmoj at 19/04/15.
 */
@Product(id = "codetrack-shell")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BannerProvider extends DefaultBannerProvider {

    Logger logger = LoggerFactory.getLogger(BannerProvider.class);

    @Autowired
    private ShellApplicationInfo shellApplicationInfo;

    /**
     * Access banner ASCII art
     * @return String banner art
     */
    @Override
    public String getBanner() {

        logger.info("Preparing banner...");

        StringBuffer banner = new StringBuffer();

        banner.append("\n\n");
        banner.append("   ___          _      _                  _      __ _          _ _\n");
        banner.append("  / __\\___   __| | ___| |_ _ __ __ _  ___| | __ / _\\ |__   ___| | |\n");
        banner.append(" / /  / _ \\ / _` |/ _ \\ __| '__/ _` |/ __| |/ / \\ \\| '_ \\ / _ \\ | |\n");
        banner.append("/ /__| (_) | (_| |  __/ |_| | | (_| | (__|   <  _\\ \\ | | |  __/ | |\n");
        banner.append("\\____/\\___/ \\__,_|\\___|\\__|_|  \\__,_|\\___|_|\\_\\ \\__/_| |_|\\___|_|_|\n");
        banner.append("\n\n");

        logger.info(banner.toString());

        return banner.toString();

    }

    /**
     * Access to banner provider name
     *
     * @return String provider name
     */
    @Override
    public String getProviderName() {
        return "codetrack banner provider";
    }

    /**
     * Access the version of banner provider
     *
     * @return String with version number
     */
    @Override
    public String getVersion() {
        return shellApplicationInfo.getVersion();
    }

    /**
     * Access to welcome message
     *
     * @return
     */
    @Override
    public String getWelcomeMessage() {
        return shellApplicationInfo.getWelcome();
    }
}
