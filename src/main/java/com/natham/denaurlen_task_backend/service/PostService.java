package com.natham.denaurlen_task_backend.service;

import com.natham.denaurlen_task_backend.model.PostData;
import com.natham.denaurlen_task_backend.model.UserCredential;
import com.natham.denaurlen_task_backend.model.Valuation;
import com.natham.denaurlen_task_backend.repo.PostDataRepository;
import com.natham.denaurlen_task_backend.repo.UserCredentialRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    private final UserCredentialRepository userRepo;
    private final PostDataRepository postRepo;

    public PostService(UserCredentialRepository userRepo, PostDataRepository postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }


    public PostData initializePost() {
        PostData post = new PostData();
        post.setLeadUser("terry_dias");
        post.setNetCoins(1100);
        post.setGrossCoins(2100);
        post.setCreatedAt(LocalDateTime.now());
        System.out.println("Initialized post Successfully");
        return postRepo.save(post);
    }

    public PostData getPostData() {
        System.out.println("Requested post data");
        return postRepo.findAll().get(0);
    }

    public PostData performValuation(String username) {
        UserCredential user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));

        PostData post = postRepo.findAll().get(0);

        if (post.getCreatedAt().plusHours(144).isBefore(LocalDateTime.now())) {
            System.out.println("Valuation period expired.");
            throw new RuntimeException("Valuation period expired.");
        }

        int newNetCoins = post.getNetCoins() + 100;
        int newGrossCoins = post.getGrossCoins() + newNetCoins;

        post.setLeadUser(username);
        post.setNetCoins(newNetCoins);
        post.setGrossCoins(newGrossCoins);

        Valuation valuation = new Valuation(user.getName(), username, newNetCoins, newGrossCoins);
        post.getValuationHistory().add(valuation);

        System.out.println("Lead Successfully updated");
        return postRepo.save(post);
    }
}