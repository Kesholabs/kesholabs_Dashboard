package com.kesholabs.mpesadashboard.repo.Kesho;

import com.kesholabs.mpesadashboard.entity.Kesho.Dashboard_UsersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dashboard_UsersRepo extends MongoRepository<Dashboard_UsersEntity, String> {
    Dashboard_UsersEntity findByEmail(String email);

}
