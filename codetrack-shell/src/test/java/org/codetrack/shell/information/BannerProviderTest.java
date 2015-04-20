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

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class BannerProviderTest extends TestCase {


    @Test
    public void testGetProviderName() throws Exception {

        BannerProvider bp = new BannerProvider();

        assertSame("Codetrack banner provider", bp.getProviderName());

    }

    @Test
    public void testGetVersion() throws Exception {

        BannerProvider bp = new BannerProvider();

        assertSame("V1.0", bp.getVersion());

    }

    @Test
    public void testGetWelcomeMessage() throws Exception {

        BannerProvider bp = new BannerProvider();

        assertSame("Welcome to Codetrack tool", bp.getWelcomeMessage());

    }
}