package com.example.castaticanalyzer.authentication;

import com.example.castaticanalyzer.mail.EmailSender;
import com.example.castaticanalyzer.user.Role;
import com.example.castaticanalyzer.user.User;
import com.example.castaticanalyzer.user.UserDataGateway;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationService {
    private UserDataGateway userRepository;
    private PasswordEncoder encoder;
    private EmailSender sender;


    public AuthenticationService(UserDataGateway userRepository, PasswordEncoder encoder, EmailSender sender) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.sender = sender;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user != null)
        {
            user.setActive(true);
            return true;
        }
        return false;
    }


    public void save(User user) throws MessagingException {
        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(Role.USER);
        user.setRoles(rolesSet);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());

        String message = String.format(
                "Hello, dear User!\nWelcome to Clean Architecture Analyzer. Please, visit this link: " +
                        "<a href=\"http:/localhost:8080/activate/%s\">Activate your account</a>",
                user.getActivationCode()
        );
        sender.send(user.getUsername(), "Activate your account. Clean Architecture Analyzer", message);
        System.err.println(user);
        System.err.println(user.getPassword().length());
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
