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

/**
 * @author josecmoj at 25/04/15.
 */
@Product(id = "codetrack-shell")
@Reference(id = "shell.properties")
public class ShellFileInfo {

    private String historyFileName;

    public ShellFileInfo(String historyFileName) {
        this.historyFileName = historyFileName;
    }

    public String getHistoryFileName() {
        return historyFileName;
    }

    public void setHistoryFileName(String historyFileName) {
        this.historyFileName = historyFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShellFileInfo that = (ShellFileInfo) o;

        if (historyFileName != null ? !historyFileName.equals(that.historyFileName) : that.historyFileName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return historyFileName != null ? historyFileName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ShellFileInfo{" +
                "historyFileName='" + historyFileName + '\'' +
                '}';
    }
}
