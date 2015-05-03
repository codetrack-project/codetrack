/*
 * Copyright 2015 the original author or authors members of codetrack.org
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

package org.codetrack.database;

import com.google.common.base.Objects;

/**
 * Database parameters store class
 *
 * @author josecmoj at 25/04/15.
 */
public class DatabaseParameters {

    /**
     * Name of database
     */
    private String name;

    /**
     * User name to access database
     */
    private String user;

    /**
     * Password name to access database
     */
    private String password;

    /**
     * URL access to database
     */
    private String url;

    /**
     * Path to database location
     */
    private String path;

    /**
     * Database Engine
     */
    private DatabaseEngine engine;

    /**
     * Default constructor
     */
    public DatabaseParameters() {
    }

    /**
     * Constructor with builder instance
     *
     * @param builder
     */
    private DatabaseParameters(Builder builder) {
        setEngine(builder.engine);
        setName(builder.name);
        setUser(builder.user);
        setPassword(builder.password);
        setUrl(builder.url);
        path = builder.path;
    }

    /**
     * Get new DatabaseParameters.Builder instance
     *
     * @return new builder instance
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public DatabaseEngine getEngine() {
        return engine;
    }

    public void setEngine(DatabaseEngine engine) {
        this.engine = engine;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DatabaseParameters that = (DatabaseParameters) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (engine != null ? !engine.equals(that.engine) : that.engine != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("user", user)
                .add("password", password)
                .add("url", url)
                .add("engine", engine)
                .add("path", path)
                .toString();
    }

    /**
     * Database.Builder class
     */
    public static final class Builder {
        private DatabaseEngine engine;
        private String name;
        private String user;
        private String password;
        private String url;
        private String path;

        private Builder() {
        }

        public Builder engine(final DatabaseEngine engine) {
            this.engine = engine;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder user(final String user) {
            this.user = user;
            return this;
        }

        public Builder password(final String password) {
            this.password = password;
            return this;
        }

        public Builder url(final String url) {
            this.url = url;
            return this;
        }

        public Builder path(final String path) {
            this.path = path;
            return this;
        }

        public DatabaseParameters build() {
            return new DatabaseParameters(this);
        }
    }
}
