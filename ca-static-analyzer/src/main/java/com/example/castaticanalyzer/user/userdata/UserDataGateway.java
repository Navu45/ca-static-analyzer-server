package com.example.castaticanalyzer.user.userdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataGateway extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
