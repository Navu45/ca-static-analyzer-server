package com.example.castaticanalyzer.authentication;

import com.example.castaticanalyzer.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.MessagingException;
/** @UseCase */

@Component
public class AuthenticationBoundary {
    AuthenticationService authenticationService;
    ValidationUtil validationUtil;

    public AuthenticationBoundary(AuthenticationService authenticationService, ValidationUtil validationUtil) {
        this.authenticationService = authenticationService;
        this.validationUtil = validationUtil;
    }
    
    public String handleRegistrationRequest(User user, BindingResult bindingResult, Model model) throws MessagingException {
        boolean userExists = authenticationService.findByUsername(user.getUsername()) != null;
        boolean inputInvalid = bindingResult.hasErrors();
        boolean notConfirmedPassword = user.getPassword() != null
                && !user.getPassword().equals(user.getConfirmPassword());

        System.err.println(userExists + " " + inputInvalid + " " + notConfirmedPassword);
        if (userExists || inputInvalid || notConfirmedPassword)
        {
            if (inputInvalid)
            {
                model.mergeAttributes(validationUtil.getErrors(bindingResult));
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

        authenticationService.save(user);
        model.addAttribute("message", "Activate your account. Activation code was sent to " + user.getUsername());

        return "login";
    }

    public String handleLoginRequest(Model model, String error, String logout) {
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

    public String handleActivationRequest(Model model, String code) {
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
