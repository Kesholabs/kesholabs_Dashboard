package com.kesholabs.mpesadashboard.repo;

import com.kesholabs.mpesadashboard.entity.Dashboard_UsersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dashboard_UsersRepo extends MongoRepository<Dashboard_UsersEntity, String> {
    Dashboard_UsersEntity findByEmail(String email);

}
