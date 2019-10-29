package com.kesholabs.mpesadashboard.dao;

import com.kesholabs.mpesadashboard.entity.WavuUsersEntity;

import java.util.List;

public interface WavuUsersDao {
     int getAllWavuUsers();
     int getNewWavuUsers(String date);
     int getAllVerifiedUsers();
     int getAllUnverifiedUsers();
     int getAllVerifiedEmail(Boolean status);
     int getAllVerifiedPhone(Boolean status);
     List<WavuUsersEntity> getUsersProfileByPhone(String query);
     List<WavuUsersEntity> getUsersProfileByEmail(String query);
}
