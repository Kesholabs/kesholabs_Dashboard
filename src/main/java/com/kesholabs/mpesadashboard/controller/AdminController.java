package com.kesholabs.mpesadashboard.controller;

import com.kesholabs.mpesadashboard.entity.Dashboard_UsersEntity;
import com.kesholabs.mpesadashboard.repo.Dashboard_UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    Dashboard_UsersRepo dashboard_usersRepo;

    @GetMapping("/admin/permission")
    public ModelAndView Users(){
        ModelAndView mv = new ModelAndView("Roles/roles");
        mv.addObject("users", dashboard_usersRepo.findAll());
        return mv;
    }

}
