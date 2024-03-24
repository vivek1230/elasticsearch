package com.example.elasticsearch.repo;

import com.example.elasticsearch.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ElasticsearchRepository<User, Integer> {
}
