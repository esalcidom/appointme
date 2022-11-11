package com.service.appointmentme.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void successGetUserByUserName(){
        Optional<User> user = userRepository.findUserByUsername("myUser");
        Assertions.assertEquals(user.get().getId(), 1);
    }

    @Test
    public void notFoundGetUserByUserName(){
        Optional<User> user = userRepository.findUserByUsername("otherUser");
        Assertions.assertTrue(user.isEmpty());
    }
}
