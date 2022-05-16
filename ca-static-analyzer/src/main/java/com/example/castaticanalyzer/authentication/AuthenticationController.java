package com.example.castaticanalyzer.authentication;

import com.example.castaticanalyzer.authentication.security.JwtTokenUtil;
import com.example.castaticanalyzer.authentication.user.User;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;


/** @InterfaceAdapter */

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication")
@Slf4j
public class AuthenticationController {
    private final AuthenticationBoundary boundary;

    private final AuthenticationService service;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/registration")
    public ResponseEntity<Object> registration(@RequestBody @Valid User userForm,
                                               BindingResult bindingResult) throws MessagingException {
        Map<String, Object> response = new HashMap<>();
        User user = boundary.register(response, userForm, bindingResult);
        response.put("status", HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid User userRequest, BindingResult bindingResult) {

        Map<String, Object> response = new HashMap<>();
        User user = boundary.authenticate(userRequest, bindingResult);
        response.put("user", user);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(user))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION)
                .body(response);
    }

    @GetMapping("/token")
    public ResponseEntity<Object> evaluateToken()
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok()
                .body(user);
    }

    @GetMapping("activate/{code}")
    public String activate(Model model,
                           @PathVariable String code){
        return boundary.activateUser(model, code);
    }

    @GetMapping("users/all")
    public ResponseEntity<Object> getAllUsers(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}
