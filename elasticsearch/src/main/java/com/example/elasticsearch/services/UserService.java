package com.example.elasticsearch.services;

import com.example.elasticsearch.entity.User;
import com.example.elasticsearch.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ElasticSearchService elasticSearchService;

    public User insertUser(User user){
        return userRepo.save(user);
    }

    public Iterable<User> getUsers(){
        return userRepo.findAll();
    }
}

