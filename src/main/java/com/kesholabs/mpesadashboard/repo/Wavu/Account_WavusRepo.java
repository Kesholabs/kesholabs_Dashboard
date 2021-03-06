package com.kesholabs.mpesadashboard.repo.Wavu;

import com.kesholabs.mpesadashboard.entity.Wavu.Account_Wavus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Account_WavusRepo extends MongoRepository<Account_Wavus, String> {
    Account_Wavus findByTokenName(String token);
}
