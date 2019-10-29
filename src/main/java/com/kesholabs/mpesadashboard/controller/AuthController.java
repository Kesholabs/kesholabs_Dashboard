package com.kesholabs.mpesadashboard.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @GetMapping("/signin")
    public String login(){
            return "Login/login";
    }

    @GetMapping("/signup")
    public String register(){
        return "Register/signup";
    }

    @GetMapping("/forgotpassword")
    public String forgot(){
        return "Forgot/forgot";
    }

    @GetMapping("/resetpassword")
    public String resetPassword(){
        return "ResetPassword/reset";
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
