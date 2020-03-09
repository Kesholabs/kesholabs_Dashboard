package com.kesholabs.mpesadashboard.controller;

import com.kesholabs.mpesadashboard.dao.impl.Account_AionDaoImpl;
import com.kesholabs.mpesadashboard.dao.impl.Account_WavuDaoImpl;
import com.kesholabs.mpesadashboard.dao.impl.WavuUsersDaoImpl;
import com.kesholabs.mpesadashboard.entity.Kesho.Dashboard_UsersEntity;
import com.kesholabs.mpesadashboard.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = "/mpesa/dashboard")
public class MpesaController {

    @Autowired
    WavuUsersDaoImpl wavuUsersDaoImpl;

    @Autowired
    Account_WavuDaoImpl account_wavuDaoImpl;

    @Autowired
    Account_AionDaoImpl account_aionDaoImpl;

    @Autowired
    private CustomUserDetailsService userService;

    Date todaysDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = formatter.format(todaysDate);


    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public ModelAndView dashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Dashboard_UsersEntity user = userService.findUserByEmail(auth.getName());
        ModelAndView mv = new ModelAndView("Mpesa/mpesa");
        mv.addObject("allusers", wavuUsersDaoImpl.getAllWavuUsers());
        mv.addObject("newusers", wavuUsersDaoImpl.getNewWavuUsers(formattedDate));
        mv.addObject("getallverified",wavuUsersDaoImpl.getAllVerifiedUsers());
        mv.addObject("getallunverified",wavuUsersDaoImpl.getAllUnverifiedUsers());
        mv.addObject("getallverifiedemail",wavuUsersDaoImpl.getAllVerifiedEmail(true));
        mv.addObject("getallverifiedphone",wavuUsersDaoImpl.getAllVerifiedPhone(true));
        mv.addObject("getallunverifiedemail",wavuUsersDaoImpl.getAllVerifiedEmail(false));
        mv.addObject("getallunverifiedphone",wavuUsersDaoImpl.getAllVerifiedPhone(false));
        mv.addObject("wavubalance",account_wavuDaoImpl.getWavuBalance());
        mv.addObject("wavuspent",account_wavuDaoImpl.getWavuSpent());
        mv.addObject("aionbalance",account_aionDaoImpl.getAionBalance());
        mv.addObject("aionspent",account_aionDaoImpl.getAionSpent());
        mv.addObject("currentUser", user);
        mv.addObject("username", user.getUsername());
        mv.addObject("date", user.getDate());
        mv.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        return mv;
    }
}
