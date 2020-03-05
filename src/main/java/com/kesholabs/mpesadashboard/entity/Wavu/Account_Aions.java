package com.kesholabs.mpesadashboard.entity.Wavu;

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
@Document(collection = "aions")
public class Account_Aions {
    @Id
    ObjectId _id;
    float totalAionIn;
    float totalAionOut;
    float remainingAion;
    String tokenName;
    Date createdAt;
}
