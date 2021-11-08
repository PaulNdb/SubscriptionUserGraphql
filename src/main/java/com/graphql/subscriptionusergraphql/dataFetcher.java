package com.graphql.subscriptionusergraphql;

import graphql.schema.DataFetchingEnvironment;

public interface dataFetcher<T> {
    T get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception;
}