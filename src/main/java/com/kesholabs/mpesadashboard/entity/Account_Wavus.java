package com.kesholabs.mpesadashboard.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wavus")
public class Account_Wavus {
    @Id
    ObjectId _id;
    float totalWavuIn;
    float totalWavuOut;
    float remainingWavu;
    String tokenName;
    Date createdAt;
}
