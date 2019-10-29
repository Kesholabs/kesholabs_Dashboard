package com.kesholabs.mpesadashboard.repo;

import com.kesholabs.mpesadashboard.entity.Account_Aions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Account_AionsRepo extends MongoRepository<Account_Aions, String> {
    Account_Aions findByTokenName(String token);
}
