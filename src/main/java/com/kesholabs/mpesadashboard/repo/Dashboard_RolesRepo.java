package com.kesholabs.mpesadashboard.repo;

import com.kesholabs.mpesadashboard.entity.Dashboard_RolesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dashboard_RolesRepo extends MongoRepository<Dashboard_RolesEntity, String> {
    Dashboard_RolesEntity findByRole(String role);
}
