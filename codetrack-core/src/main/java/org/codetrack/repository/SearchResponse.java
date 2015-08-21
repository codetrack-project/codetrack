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

package org.codetrack.repository;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author josecmoj at 11/07/15.
 */
public class SearchResponse<T> {

    private List<T> items;

    private T item;

    private SearchRequest<T> request;

    private SearchResponse(Builder<T> builder) {
        setItems(builder.items);
        setItem(builder.item);
        setRequest(builder.request);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(SearchResponse copy) {
        Builder builder = new Builder();
        builder.items = copy.items;
        builder.item = copy.item;
        builder.request = copy.request;
        return builder;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T getItem() {
        return item;
    }

    public SearchRequest<T> getRequest() {
        return request;
    }

    public void setRequest(SearchRequest<T> request) {
        this.request = request;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchResponse)) return false;
        SearchResponse<?> that = (SearchResponse<?>) o;
        return Objects.equal(getItems(), that.getItems()) &&
                Objects.equal(getItem(), that.getItem()) &&
                Objects.equal(getRequest(), that.getRequest());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getItems(), getItem(), getRequest());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("items", items)
                .add("item", item)
                .add("request", request)
                .toString();
    }

    public static final class Builder<T> {
        private List<T> items;
        private T item;
        private SearchRequest<T> request;

        private Builder() {
        }

        public Builder items(final List<T> items) {
            this.items = items;
            return this;
        }

        public Builder item(final T item) {
            this.item = item;
            return this;
        }

        public Builder request(final SearchRequest<T> request) {
            this.request = request;
            return this;
        }

        public SearchResponse build() {
            return new SearchResponse(this);
        }

        public void addItem(T item) {
            if (items == null)
                items = Lists.newArrayList();

            items.add(item);
        }
    }
}
