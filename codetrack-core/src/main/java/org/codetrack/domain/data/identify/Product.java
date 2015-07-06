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

package org.codetrack.domain.data.identify;


import com.google.common.base.Objects;
import org.codetrack.domain.data.ProjectItem;
import org.codetrack.domain.data.definition.Feature;
import org.codetrack.domain.data.definition.Story;
import org.codetrack.domain.data.definition.UseCase;

import java.util.Map;

/**
 * @author josecmoj at 29/05/15.
 */
@org.codetrack.annotation.identify.Product(id = "codetrack-core")
@org.codetrack.annotation.definition.Feature(id = "#4-DATABASE")
public class Product extends ProjectItem {

    private String id;

    private String name;

    private String description;

    private Map<String, Feature> features;

    private Map<String, UseCase> useCases;

    private Map<String, Story> stories;

    private Map<String, Source> sources;

    public Product() {
        super();
    }

    private Product(Builder builder) {
        setDescription(builder.description);
        setId(builder.id);
        setName(builder.name);
        setFeatures(builder.features);
        setUseCases(builder.useCases);
        setStories(builder.stories);
        setSources(builder.sources);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Product copy) {
        Builder builder = new Builder();
        builder.description = copy.description;
        builder.id = copy.id;
        builder.name = copy.name;
        builder.features = copy.features;
        builder.useCases = copy.useCases;
        builder.stories = copy.stories;
        builder.sources = copy.sources;
        return builder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Feature> features) {
        this.features = features;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Source> getSources() {
        return sources;
    }

    public void setSources(Map<String, Source> sources) {
        this.sources = sources;
    }

    public Map<String, Story> getStories() {
        return stories;
    }

    public void setStories(Map<String, Story> stories) {
        this.stories = stories;
    }

    public Map<String, UseCase> getUseCases() {
        return useCases;
    }

    public void setUseCases(Map<String, UseCase> useCases) {
        this.useCases = useCases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equal(getId(), product.getId()) &&
                Objects.equal(getName(), product.getName()) &&
                Objects.equal(getDescription(), product.getDescription()) &&
                Objects.equal(getFeatures(), product.getFeatures()) &&
                Objects.equal(getUseCases(), product.getUseCases()) &&
                Objects.equal(getStories(), product.getStories()) &&
                Objects.equal(getSources(), product.getSources());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName());
    }

    public static final class Builder {
        private String description;
        private String id;
        private String name;
        private Map<String, Feature> features;
        private Map<String, UseCase> useCases;
        private Map<String, Story> stories;
        private Map<String, Source> sources;

        private Builder() {
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder features(final Map<String, Feature> features) {
            this.features = features;
            return this;
        }

        public Builder useCases(final Map<String, UseCase> useCases) {
            this.useCases = useCases;
            return this;
        }

        public Builder stories(final Map<String, Story> stories) {
            this.stories = stories;
            return this;
        }

        public Builder sources(final Map<String, Source> sources) {
            this.sources = sources;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
