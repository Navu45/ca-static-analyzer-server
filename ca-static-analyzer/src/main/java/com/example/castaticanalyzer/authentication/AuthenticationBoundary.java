package com.example.castaticanalyzer.authentication;

import com.example.castaticanalyzer.authentication.exceptions.BadUserDataInputException;
import com.example.castaticanalyzer.authentication.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.mail.MessagingException;
import java.util.*;

/** @UseCase */

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationBoundary {
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    public User register(Map<String, Object> response,
                         User user, BindingResult bindingResult) throws MessagingException {
        boolean userExists = authenticationService.findByUsername(user.getUsername()) != null;
        boolean invalidInput = bindingResult.hasErrors();
        boolean notConfirmedPassword = user.getPassword() != null
                && !user.getPassword().equals(user.getConfirmPassword());

        if (userExists || invalidInput || notConfirmedPassword)
        {
            if (userExists) {
                FieldError error = new FieldError(
                        "userExistsError",
                        "username",
                        user.getUsername(),
                        false,
                        null,
                        null,
                        "User already exists with this email.");
                bindingResult.addError(error);
            }

            if (notConfirmedPassword) {
                FieldError error = new FieldError(
                        "confirmationError",
                        "password",
                        "Passwords are different.");
                bindingResult.addError(error);
            }

            throw new BadUserDataInputException(bindingResult.getFieldErrors());
        }

        response.put("message", "Activate your account. Activation code was sent to " + user.getUsername());
        return authenticationService.save(user);
    }
    public User authenticate(User userRequest, BindingResult bindingResult) {

        User user = null;
        if (bindingResult.hasErrors())
        {
            ThrowUserNotFoundException(userRequest, bindingResult);
        }
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            user = (User) auth.getPrincipal();
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            ThrowUserNotFoundException(userRequest, bindingResult);
        }
        return user;
    }

    private void ThrowUserNotFoundException(User userRequest, BindingResult bindingResult)
    {
        bindingResult.addError(new FieldError(
                "UserNotFound",
                "username",
                userRequest.getUsername(),
                false,
                null,
                null,
                "User with email " + userRequest.getUsername() + " not found"
        ));
        throw new BadUserDataInputException(bindingResult.getFieldErrors());
    }

    public String activateUser(Model model, String code) {
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
