package com.kesholabs.mpesadashboard.repo.Wavu;

import com.kesholabs.mpesadashboard.entity.Wavu.WavuUsersEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WavuUsersRepo extends PagingAndSortingRepository<WavuUsersEntity,Integer> {
    int countByActive(boolean status);
    int countByEmailVerified(boolean status);
    int countByPhoneVerified(boolean status);
    @Query("{ 'creatededAt' : { $regex: ?0 } }")
    List<WavuUsersEntity>  findWavuUsersEntityByRegexpCreatededAt(String date);
    List<WavuUsersEntity> findByPhoneNumber(String phone);
    List<WavuUsersEntity> findByEmail(String email);
}
