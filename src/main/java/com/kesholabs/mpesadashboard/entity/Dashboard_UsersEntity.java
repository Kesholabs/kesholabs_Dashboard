package com.kesholabs.mpesadashboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "Dashboard_users")
public class Dashboard_UsersEntity {
    @Id
    ObjectId _id;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @NotEmpty(message = "*Please provide your username")
    private String username;

    @NotEmpty(message = "*Please provide your full name")
    private String fullname;

    private boolean active;
    private String date;
    private Set<Dashboard_RolesEntity> roles;

    public Dashboard_UsersEntity(boolean active) {
        this.active = false;
    }
}
