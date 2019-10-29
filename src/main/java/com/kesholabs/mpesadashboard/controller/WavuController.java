package com.kesholabs.mpesadashboard.controller;

import com.kesholabs.mpesadashboard.dao.WavuUsersDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WavuController {

    @Autowired
    WavuUsersDaoImpl wavuUsersDaoImpl;

    Date todaysDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = formatter.format(todaysDate);

    @GetMapping("/home")
    public ModelAndView setView(){
        ModelAndView mv = new ModelAndView("Home/index");
        mv.addObject("allusers", wavuUsersDaoImpl.getAllWavuUsers());
        mv.addObject("newusers", wavuUsersDaoImpl.getNewWavuUsers(formattedDate));
        mv.addObject("getallverified",wavuUsersDaoImpl.getAllVerifiedUsers());
        mv.addObject("getallunverified",wavuUsersDaoImpl.getAllUnverifiedUsers());
        mv.addObject("getallverifiedemail",wavuUsersDaoImpl.getAllVerifiedEmail(true));
        mv.addObject("getallverifiedphone",wavuUsersDaoImpl.getAllVerifiedPhone(true));
        mv.addObject("getallunverifiedemail",wavuUsersDaoImpl.getAllVerifiedEmail(false));
        mv.addObject("getallunverifiedphone",wavuUsersDaoImpl.getAllVerifiedPhone(false));
        return mv;
    }


}
