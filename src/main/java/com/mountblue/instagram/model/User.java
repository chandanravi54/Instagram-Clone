package com.mountblue.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;
    private String userName;
    private String email;
    private String fullName;
    private String password;
    private byte[] profilePic=null;
    private ArrayList<String> followers = null;
    private ArrayList<String> following= null;
    private String bio =null;
    private String gender = "Not Specified";
    private String accountType = "Public";
}

