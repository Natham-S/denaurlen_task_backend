package com.natham.denaurlen_task_backend.controller;


import com.natham.denaurlen_task_backend.model.PostData;
import com.natham.denaurlen_task_backend.model.UserCredential;

import com.natham.denaurlen_task_backend.service.AuthService;
import com.natham.denaurlen_task_backend.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class AppController {

    private final PostService postService;
    private final AuthService authService;

    public AppController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;
    }


    @PostMapping("/credentials")
    public ResponseEntity<String> verifyCredentials(@RequestBody UserCredential credentials) {
        return authService.verifyCredentials(credentials);


    }


    @PostMapping("/initialize")
    public PostData initializePost() {
        return postService.initializePost();
    }


    @GetMapping("/getpostdata")
    public PostData getPostData() {
        return postService.getPostData();
    }


    @PostMapping("/lead")
    public PostData performValuation(@RequestParam String username) {
        return postService.performValuation(username);
    }
}
