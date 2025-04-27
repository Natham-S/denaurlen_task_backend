package com.natham.denaurlen_task_backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "posts")
public class PostData {
    @Id
    private String id;
    private String leadUser;
    private int netCoins;
    private int grossCoins;
    private LocalDateTime createdAt;
    private List<Valuation> valuationHistory = new ArrayList<>();
}