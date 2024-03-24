package com.example.elasticsearch.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.List;
import java.util.function.Supplier;

public class ElasticSearchUtil {


    public static MatchQuery createAutoSuggestMatchQuery(String partialText, String field){
        val autoSuggestQuery = new MatchQuery.Builder()
                .field(field)
                .query(partialText)
                .analyzer("standard").build();
        return autoSuggestQuery;
    }

    public static Supplier<Query> createSupplierAutoSuggest(String partialText, String field){
        Supplier<Query> supplier = () -> Query.of(q -> q.match(createAutoSuggestMatchQuery(partialText, field)));
        return supplier;
    }

    public static MultiMatchQuery createMultiMatchQuery(String key, List<String>  fields){
        val multiMatchQuery = new MultiMatchQuery.Builder()
                .query(key)
                .fields(fields)
                .build();
        return multiMatchQuery;
    }

    public static Supplier<Query> createSupplierMultiMatch(String key, List<String> fields){
        Supplier<Query> supplier = () -> Query.of(q -> q.multiMatch(createMultiMatchQuery(key, fields)));
        return supplier;
    }

    public static FuzzyQuery createFuzzyQuery(String approximateText, String field){
        val fuzzyQuery = new FuzzyQuery.Builder()
                .field(field)
                .value(approximateText)
                .build();
        return fuzzyQuery;
    }

    public static Supplier<Query> createSupplierFuzzyQuery(String approximateText, String field){
        Supplier<Query> supplier = () -> Query.of(q -> q.fuzzy(createFuzzyQuery(approximateText, field)));
        return supplier;
    }
}
