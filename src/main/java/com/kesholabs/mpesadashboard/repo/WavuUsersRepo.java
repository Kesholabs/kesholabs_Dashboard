package com.kesholabs.mpesadashboard.repo;

import com.kesholabs.mpesadashboard.entity.WavuUsersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WavuUsersRepo extends PagingAndSortingRepository<WavuUsersEntity,Integer> {
    int countByActive(boolean status);
    int countByEmailVerified(boolean status);
    int countByPhoneVerified(boolean status);
    int countByCreatededAt(String date);
    List<WavuUsersEntity> findByPhoneNumber(String phone);
    List<WavuUsersEntity> findByEmail(String email);
}
