package com.kesholabs.mpesadashboard.controller;

import com.kesholabs.mpesadashboard.entity.Kesho.Dashboard_UsersEntity;
import com.kesholabs.mpesadashboard.repo.Kesho.Dashboard_UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("username")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Dashboard_UsersRepo dashboard_usersRepo;

    @GetMapping("/admin/permission")
    public ModelAndView Users(){
        ModelAndView mv = new ModelAndView("Roles/roles");
        mv.addObject("users", dashboard_usersRepo.findAll());
        return mv;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView profile(@RequestParam("username") String username) {
        ModelAndView mv = new ModelAndView("Profile/profile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Dashboard_UsersEntity user = dashboard_usersRepo.findByEmail(currentUserName);
            logger.info("Current User "+currentUserName);
            mv.addObject("currentUser", user);
            mv.addObject("username", username);
            mv.addObject("role", user.getRoles().toString());
            return mv;
        }
        return mv;
    }


    @RequestMapping(value = "/adm/manageusers", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView manageUsers() {
        ModelAndView mv = new ModelAndView( "Roles/roles");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Dashboard_UsersEntity user = dashboard_usersRepo.findByEmail(currentUserName);
            mv.addObject("username", user.getUsername());
        }
        return mv;
    }

}
