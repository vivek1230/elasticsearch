package com.example.elasticsearch.services;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.elasticsearch.entity.Product;
import com.example.elasticsearch.entity.User;
import com.example.elasticsearch.utils.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<String> autoSuggestProduct(String partialText, String field) throws IOException {
        Supplier<Query> supplier= ElasticSearchUtil.createSupplierAutoSuggest(partialText, field);
        System.out.println("ES auto-suggest query " + supplier.get().toString());

        SearchResponse<Product> searchResponse = elasticsearchClient
                .search(s -> s.index("products").query(supplier.get()), Product.class);
        List<String> responseList = searchResponse.hits().hits().stream()
                .map(productHit -> productHit.source().getName()).collect(Collectors.toList());

        System.out.println("ES auto-suggest responseList " + responseList);
        return responseList;
    }

    public List<User> multiMatchUsers(String  key, List<String> fields) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.createSupplierMultiMatch(key, fields);
        System.out.println("ES multi-match query " + supplier.get().toString());

        SearchResponse<User> searchResponse = elasticsearchClient
                .search(s ->s.index("users").query(supplier.get()), User.class);
        List<User> responseList = searchResponse.hits().hits().stream()
                .map(userHit -> userHit.source()).collect(Collectors.toList());

        System.out.println("ES multi-match responseList " + responseList);
        return responseList;
    }

    public List<Product> fuzzySearchProducts(String approximateText, String field) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.createSupplierFuzzyQuery(approximateText, field);
        System.out.println("ES fuzzy-query " + supplier.get());

        SearchResponse<Product> searchResponse = elasticsearchClient
                .search(s -> s.index("products").query(supplier.get()), Product.class);
        List<Product> responseList = searchResponse.hits().hits().stream()
                .map(productHit -> productHit.source()).collect(Collectors.toList());

        System.out.println("ES fuzzy-query responseList" + responseList);
        return responseList;
    }
}
