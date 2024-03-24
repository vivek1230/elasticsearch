package com.example.elasticsearch.services;

import com.example.elasticsearch.entity.Product;
import com.example.elasticsearch.repo.ProductRepo;
import com.example.elasticsearch.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ElasticSearchService elasticSearchService;

    public Product insertProduct(Product product){
        return productRepo.save(product);
    }

    public Iterable<Product> getProducts(){
        return productRepo.findAll();
    }
}
