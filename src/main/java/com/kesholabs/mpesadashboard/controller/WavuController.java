package com.kesholabs.mpesadashboard.controller;

import com.kesholabs.mpesadashboard.dao.impl.Account_AionDaoImpl;
import com.kesholabs.mpesadashboard.dao.impl.Account_WavuDaoImpl;
import com.kesholabs.mpesadashboard.dao.impl.WavuUsersDaoImpl;
import com.kesholabs.mpesadashboard.entity.Dashboard_UsersEntity;
import com.kesholabs.mpesadashboard.entity.WavuUsersEntity;
import com.kesholabs.mpesadashboard.models.response.AjaxModelRes;
import com.kesholabs.mpesadashboard.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class WavuController {

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


    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView dashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Dashboard_UsersEntity user = userService.findUserByEmail(auth.getName());
        ModelAndView mv = new ModelAndView("Wavu/index");
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
//        mv.addObject("currentUser", user);
//        mv.addObject("fullName", "Welcome "+user.getUsername());
//        mv.addObject("date", user.getDate());
        mv.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        return mv;
    }

    @GetMapping("/allusers")
    public ModelAndView users(){
        ModelAndView mv = new ModelAndView("Wavu/allUsers");
//        mv.addObject("users",wavuUsersDaoImpl.allUsers());
        return mv;
    }

    @GetMapping("/getAllUsers")
    @ResponseBody
    public AjaxModelRes fetchAllUsers(@RequestParam("page") int page, @RequestParam("pageItems") int pageItems){
        AjaxModelRes ajaxModelRes = new AjaxModelRes();
        Pageable pageNo = PageRequest.of(page, pageItems);
        Page<WavuUsersEntity> wavuUsersEntities = wavuUsersDaoImpl.allUsers(pageNo);
        ajaxModelRes.setData(wavuUsersEntities);
        return ajaxModelRes;
    }

}
