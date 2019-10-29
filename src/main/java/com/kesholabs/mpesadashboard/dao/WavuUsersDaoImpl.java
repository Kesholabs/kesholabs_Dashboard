package com.kesholabs.mpesadashboard.dao;

import com.kesholabs.mpesadashboard.entity.WavuUsersEntity;
import com.kesholabs.mpesadashboard.repo.WavuUsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WavuUsersDaoImpl implements WavuUsersDao {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WavuUsersRepo wavuUsersRepo;

    @Override
    public int getAllWavuUsers() {
        log.info("Fetching all users "+ wavuUsersRepo.count());
        return (int) wavuUsersRepo.count();
    }

    @Override
    public int getAllVerifiedUsers() {
        return wavuUsersRepo.countByActive(true);
    }

    @Override
    public int getNewWavuUsers(String date) {
        return wavuUsersRepo.countByCreatededAt(date);
    }

    @Override
    public int getAllUnverifiedUsers() {
        return wavuUsersRepo.countByActive(false);
    }

    @Override
    public int getAllVerifiedEmail(Boolean status) {
        return wavuUsersRepo.countByEmailVerified(status);
    }

    @Override
    public int getAllVerifiedPhone(Boolean status) {
        return wavuUsersRepo.countByPhoneVerified(status);
    }

    @Override
    public List<WavuUsersEntity> getUsersProfileByPhone(String query) {
        return wavuUsersRepo.findByPhoneNumber(query);
    }

    @Override
    public List<WavuUsersEntity> getUsersProfileByEmail(String query) {
        return wavuUsersRepo.findByEmail(query);
    }
}
