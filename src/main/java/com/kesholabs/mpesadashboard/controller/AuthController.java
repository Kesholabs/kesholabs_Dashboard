package com.kesholabs.mpesadashboard.controller;


import com.kesholabs.mpesadashboard.entity.Dashboard_UsersEntity;
import com.kesholabs.mpesadashboard.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomUserDetailsService userService;

    @RequestMapping(value = {"/","/signin"}, method = RequestMethod.GET)
    public ModelAndView login(){
        logger.debug("Login process");
        ModelAndView mv = new ModelAndView("Login/login");
        return mv;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView();
        Dashboard_UsersEntity user = new Dashboard_UsersEntity();
        mv.addObject("user", user);
        mv.setViewName("Register/signup");
        return mv;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid @ModelAttribute("user") Dashboard_UsersEntity user, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        String newUser = String.format("\n Name :%s,\n username :%s,\n email :%s,\n password :%s",user.getFullname(),user.getUsername(),user.getEmail(),user.getPassword());
        logger.info("New user info "+newUser);

        try{
            Dashboard_UsersEntity userExists = userService.findUserByEmail(user.getEmail());
            if (userExists != null) {
                bindingResult
                        .rejectValue("email", "error.user",
                                "There is already a user registered with the username provided");
            }

            if (bindingResult.hasErrors()) {
                mv.addObject("errorMessage", bindingResult.getAllErrors());
                mv.setViewName("Register/signup");
                return mv;
            }

            userService.saveUser(user);
            mv.addObject("successMessage", "User has been registered successfully");
            mv.addObject("user", user);
            mv.setViewName("Register/signup");
            return mv;
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return mv;
    }

    @GetMapping("/forgotpassword")
    public String forgot(){
        return "Forgot/forgot";
    }

    @GetMapping("/resetpassword")
    public String resetPassword(){
        return "ResetPassword/reset";
    }
    // Login form with error
    @RequestMapping("/signin")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @GetMapping("/401")
    public String error401(){
        return "Error/401";
    }

    @GetMapping("/403")
    public String error403(){
        return "Error/403";
    }

    @GetMapping("/404")
    public String error404(){
        return "Error/404";
    }

    @GetMapping("/500")
    public String error500(){
        return "Error/500";
    }

    @GetMapping("/session/timeout")
    public String timeOut(){
        return "Error/timeout";
    }
}
