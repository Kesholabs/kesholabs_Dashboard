package com.kesholabs.mpesadashboard.controller;

import com.kesholabs.mpesadashboard.entity.Kesho.Dashboard_UsersEntity;
import com.kesholabs.mpesadashboard.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/ticket/dashboard")
public class TicketController {
    @Autowired
    private CustomUserDetailsService userService;

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public ModelAndView dashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Dashboard_UsersEntity user = userService.findUserByEmail(auth.getName());
        ModelAndView mv = new ModelAndView("Ticket/ticket");
        mv.addObject("allusers", "");
        return mv;
    }
}
