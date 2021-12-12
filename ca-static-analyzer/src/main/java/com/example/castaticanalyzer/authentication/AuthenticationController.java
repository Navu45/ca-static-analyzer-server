package com.example.castaticanalyzer.authentication;

import com.example.castaticanalyzer.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.validation.Valid;

/** @InterfaceAdapter */

@Controller
public class AuthenticationController {
    AuthenticationBoundary boundary;

    public AuthenticationController(AuthenticationBoundary boundary) {
        this.boundary = boundary;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("isAnalyzing", false);
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid User userForm,
                               BindingResult bindingResult, Model model) throws MessagingException {
        return boundary.handleRegistrationRequest(userForm, bindingResult, model);
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        return boundary.handleLoginRequest(model, error, logout);
    }

    @GetMapping("activate/{code}")
    public String activate(Model model,
                           @PathVariable String code){
        return boundary.handleActivationRequest(model, code);
    }

}
