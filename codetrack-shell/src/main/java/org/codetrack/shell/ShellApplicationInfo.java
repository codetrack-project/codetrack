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

/**
 * @author josecmoj at 25/04/15.
 */
@Product(id = "codetrack-shell")
public class ShellApplicationInfo {

    private String version;

    private String welcome;

    private String name;

    private String copyright;

    private String license;

    private String prompt;

    private ShellApplicationInfo(Builder builder) {
        setCopyright(builder.copyright);
        setVersion(builder.version);
        setWelcome(builder.welcome);
        setName(builder.name);
        setLicense(builder.license);
        setPrompt(builder.prompt);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShellApplicationInfo that = (ShellApplicationInfo) o;

        if (copyright != null ? !copyright.equals(that.copyright) : that.copyright != null) return false;
        if (license != null ? !license.equals(that.license) : that.license != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (prompt != null ? !prompt.equals(that.prompt) : that.prompt != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (welcome != null ? !welcome.equals(that.welcome) : that.welcome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (welcome != null ? welcome.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (copyright != null ? copyright.hashCode() : 0);
        result = 31 * result + (license != null ? license.hashCode() : 0);
        result = 31 * result + (prompt != null ? prompt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShellApplicationInfo{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }


    public static final class Builder {
        private String copyright;
        private String version;
        private String welcome;
        private String name;
        private String license;
        private String prompt;

        private Builder() {
        }

        public Builder copyright(final String copyright) {
            this.copyright = copyright;
            return this;
        }

        public Builder version(final String version) {
            this.version = version;
            return this;
        }

        public Builder welcome(final String welcome) {
            this.welcome = welcome;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder license(final String license) {
            this.license = license;
            return this;
        }

        public Builder prompt(final String prompt) {
            this.prompt = prompt;
            return this;
        }

        public ShellApplicationInfo build() {
            return new ShellApplicationInfo(this);
        }
    }
}
