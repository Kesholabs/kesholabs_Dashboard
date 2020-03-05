package com.kesholabs.mpesadashboard.controller;


import com.kesholabs.mpesadashboard.entity.Kesho.Dashboard_RolesEntity;
import com.kesholabs.mpesadashboard.entity.Kesho.Dashboard_UsersEntity;
import com.kesholabs.mpesadashboard.models.request.UserModel;
import com.kesholabs.mpesadashboard.repo.Kesho.Dashboard_UsersRepo;
import com.kesholabs.mpesadashboard.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@Controller
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomUserDetailsService userService;
    @Autowired
    Dashboard_UsersRepo dashboard_usersRepo;

    @RequestMapping(value = {"/","/signin"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("Login/login");
        logger.debug("Login process");
        UserModel userModel = new UserModel();
        mv.addObject("user", userModel);
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
        ModelAndView mv = new ModelAndView("Register/signup");
        logger.info("New user info \n"+user.toString());

        try{
            Dashboard_UsersEntity userExists = userService.findUserByEmail(user.getEmail());
//            logger.error("USER ALREADY EXIST "+userExists.toString());
            if (userExists != null) {
                bindingResult
                        .rejectValue("email", "error.user",
                                "There is already a user registered with the username provided");
            }

            if (bindingResult.hasErrors()) {
                logger.error("ERRORS "+bindingResult.getAllErrors());
                mv.addObject("errorMessage", bindingResult.getAllErrors());
                return mv;
            }

            userService.saveUser(user);
            mv.addObject("successMessage", "User has been registered successfully");
            mv.addObject("user", user);
            return mv;
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return mv;
    }

    @RequestMapping("/default")
    public String defaultAfterLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Dashboard_UsersEntity user = dashboard_usersRepo.findByEmail(currentUserName);
            logger.info("Username "+user.getUsername());

            String forward = "redirect:/profile?username="+user.getUsername();

            switch (user.getDepartment()){
                case "ADMIN":
                    return forward;
                case "DEVELOPER":
                    return forward;
                case "FINANCE":
                    return forward;
                case "MARKET":
                    return forward;
                case "SALES":
                    return forward;
                default:
                    return "Error/401";
            }

        }
        return "Error/401";
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
