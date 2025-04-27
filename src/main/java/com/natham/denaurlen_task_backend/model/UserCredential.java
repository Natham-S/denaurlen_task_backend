package com.natham.denaurlen_task_backend.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserCredential {
    @Id
    private String id;
    private String name;
    private String username;
}
