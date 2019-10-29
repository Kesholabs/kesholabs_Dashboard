package com.kesholabs.mpesadashboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "users")
public class WavuUsersEntity {
    @Id
    ObjectId _id;
    String role;
    Boolean emailVerified;
    Boolean phoneVerified;
    Boolean active;
    List referrals;
    List successfulReferrals;
    float receivedAion;
    float receivedWavu;
    List verificationToken;
    String firstName;
    String lastName;
    String email;
    String referralid;
    String password;
    Date updatedAt;
    Date creatededAt;
    String phoneNumber;
}
