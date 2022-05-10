package com.example.castaticanalyzer.authentication.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/** @UseCase */
@Repository
public interface UserDataGateway extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
