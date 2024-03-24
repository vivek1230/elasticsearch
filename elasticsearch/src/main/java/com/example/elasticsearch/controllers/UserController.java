package com.example.elasticsearch.controllers;

import com.example.elasticsearch.entity.User;
import com.example.elasticsearch.services.ElasticSearchService;
import com.example.elasticsearch.services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/user/")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ElasticSearchService elasticSearchService;

    @PostMapping("/insert")
    User insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    @GetMapping("/fetchAll")
    Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/multiMatch/{key}")
    Iterable<User> getUsers(@PathVariable String key,
                            @RequestParam List<String> fields) throws IOException {
        return elasticSearchService.multiMatchUsers(key, fields);
    }

}
