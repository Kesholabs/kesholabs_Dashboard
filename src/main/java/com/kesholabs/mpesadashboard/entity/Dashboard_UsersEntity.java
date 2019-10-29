package com.kesholabs.mpesadashboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "Dashboard_users")
public class Dashboard_UsersEntity {
    @Id
    ObjectId _id;
    String email;
    String username;
    String password;
    String roles;
}
