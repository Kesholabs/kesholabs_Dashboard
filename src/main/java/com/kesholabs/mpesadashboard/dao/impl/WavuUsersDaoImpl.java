package com.kesholabs.mpesadashboard.dao.impl;

import com.kesholabs.mpesadashboard.dao.Wavu_UsersDao;
import com.kesholabs.mpesadashboard.entity.WavuUsersEntity;
import com.kesholabs.mpesadashboard.repo.WavuUsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WavuUsersDaoImpl implements Wavu_UsersDao {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WavuUsersRepo wavuUsersRepo;

    @Override
    public Page<WavuUsersEntity> allUsers(Pageable firstNo) {
        return wavuUsersRepo.findAll(firstNo);
    }

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
