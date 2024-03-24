package com.example.elasticsearch.controllers;


import com.example.elasticsearch.entity.Product;
import com.example.elasticsearch.services.ElasticSearchService;
import com.example.elasticsearch.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/product/")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ElasticSearchService elasticSearchService;

    @PostMapping("/insert")
    Product insertProduct(@RequestBody Product product){
        return productService.insertProduct(product);
    }

    @GetMapping("/fetchAll")
    Iterable<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/autoSuggest/{partialText}")
    List<String> autoSuggestProduct(@PathVariable String partialText,
                             @RequestParam(value = "field") String field) throws IOException {
        return elasticSearchService.autoSuggestProduct(partialText, field);
    }

    @GetMapping("/fuzzySearch/{approximateText}")
    List<Product> fuzzySearchProducts(@PathVariable String approximateText,
                              @RequestParam(value = "field") String field) throws IOException {
        return elasticSearchService.fuzzySearchProducts(approximateText, field);
    }
}
