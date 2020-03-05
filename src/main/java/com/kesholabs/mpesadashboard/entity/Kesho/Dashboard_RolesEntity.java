package com.kesholabs.mpesadashboard.entity.Kesho;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Dashboard_roles")
public class Dashboard_RolesEntity {
    @Id
    ObjectId _id;
    String role;
}
