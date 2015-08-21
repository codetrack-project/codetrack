package org.codetrack.repository;

/**
 * @author josecmoj at 20/08/15.
 */
public class SearchAll<T> implements SearchRequest {

    private Class<T> itemClazz;

    private SearchAll(Builder builder) {
        setItemClazz(builder.itemClazz);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(SearchAll copy) {
        Builder builder = new Builder();
        builder.itemClazz = copy.itemClazz;
        return builder;
    }

    public Class<T> getItemClazz() {
        return itemClazz;
    }

    public void setItemClazz(Class<T> itemClazz) {
        this.itemClazz = itemClazz;
    }

    public static final class Builder {

        private Class itemClazz;

        private Builder() {
        }

        public Builder itemClazz(final Class itemClazz) {
            this.itemClazz = itemClazz;
            return this;
        }

        public SearchAll build() {
            return new SearchAll(this);
        }
    }
}
