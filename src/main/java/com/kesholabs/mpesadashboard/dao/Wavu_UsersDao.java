package com.kesholabs.mpesadashboard.dao;

import com.kesholabs.mpesadashboard.entity.WavuUsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Wavu_UsersDao {
     Page<WavuUsersEntity> allUsers(Pageable pageNo);
     int getAllWavuUsers();
     int getNewWavuUsers(String date);
     int getAllVerifiedUsers();
     int getAllUnverifiedUsers();
     int getAllVerifiedEmail(Boolean status);
     int getAllVerifiedPhone(Boolean status);
     List<WavuUsersEntity> getUsersProfileByPhone(String query);
     List<WavuUsersEntity> getUsersProfileByEmail(String query);
}
