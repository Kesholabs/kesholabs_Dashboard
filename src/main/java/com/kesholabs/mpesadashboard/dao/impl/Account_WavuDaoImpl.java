package com.kesholabs.mpesadashboard.dao.impl;

import com.kesholabs.mpesadashboard.dao.Account_WavuDao;
import com.kesholabs.mpesadashboard.repo.Wavu.Account_WavusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Account_WavuDaoImpl implements Account_WavuDao {
    @Autowired
    Account_WavusRepo account_wavusRepo;

    @Override
    public double getWavuBalance() {
        return account_wavusRepo.findByTokenName("wavu").getRemainingWavu();
    }

    @Override
    public double getWavuSpent() {
        return account_wavusRepo.findByTokenName("wavu").getTotalWavuOut();
    }
}
