package com.example.castaticanalyzer.user.authentication;

import com.example.castaticanalyzer.user.userdata.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
public class AuthenticationController {
    AuthenticationService authenticationService;
    ValidationUtil validationUtil;

    public AuthenticationController(AuthenticationService authenticationService,
                                    ValidationUtil validationUtil) {
        this.authenticationService = authenticationService;
        this.validationUtil = validationUtil;
    }

    @GetMapping("/home")
    public String home() {
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
        System.out.println(userForm.toString());
        boolean userExists = authenticationService.findByUsername(userForm.getUsername()) != null;
        boolean inputInvalid = bindingResult.hasErrors();
        boolean notConfirmedPassword = userForm.getPassword() != null
                && !userForm.getPassword().equals(userForm.getConfirmPassword());

        System.err.println(userExists + " " + inputInvalid + " " + notConfirmedPassword);
        if (userExists || inputInvalid || notConfirmedPassword)
        {
            if (inputInvalid)
            {
                validationUtil.getErrors(bindingResult, model);
            }

            if (userExists)
            {
                model.addAttribute("userExistsError", "User already exists with this email.");
            }

            if (notConfirmedPassword) {
                model.addAttribute("confirmationError", "Passwords are different.");
            }
            return "registration";
        }

        authenticationService.save(userForm);
        model.addAttribute("message", "Activate your account. Activation code was sent to " + userForm.getUsername());

        return "login";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        User user = authenticationService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user != null ) {
            if (!user.isActive()){
                model.addAttribute("error", "Account is not activated." +
                        " Activation code was sent to" + user.getUsername());
                return "login";
            }
            return "redirect:/home";
        }

        if (error != null)
            model.addAttribute("error", "Username or/and password is incorrect.");

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    @GetMapping("activate/{code}")
    public String activate(Model model,
                           @PathVariable String code){
        boolean isActivated = authenticationService.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "User successfully activated.");
        }
        else {
            model.addAttribute("message", "Activation code is not found.");
        }
        return "login";
    }

}
