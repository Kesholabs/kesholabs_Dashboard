package com.kesholabs.mpesadashboard.dao.impl;

import com.kesholabs.mpesadashboard.dao.Account_AionDao;
import com.kesholabs.mpesadashboard.repo.Account_AionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Account_AionDaoImpl implements Account_AionDao {

    @Autowired
    Account_AionsRepo account_aionsRepo;

    @Override
    public double getAionBalance() {
        return account_aionsRepo.findByTokenName("aion").getRemainingAion();
    }

    @Override
    public double getAionSpent() {
        return account_aionsRepo.findByTokenName("aion").getTotalAionOut();
    }
}
