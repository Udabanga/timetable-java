package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.payload.request.LoginRequest;
import com.udayanga.timetableio.payload.request.SignupRequest;
import com.udayanga.timetableio.payload.response.MessageResponse;
import com.udayanga.timetableio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AuthController authController;

    @Test
    void registerUserTest() {

        SignupRequest signUpRequest = new SignupRequest("Test1", "test1@test.com", "password");
        authController.registerUser(signUpRequest);

        assertEquals(userRepository.existsByEmail("test1@test.com"), true);
    }

    @Test
    void authenticateUser() {
        LoginRequest loginRequest = new LoginRequest("test1@test.com", "password");
        MessageResponse loginResponse = authController.authenticateUser(loginRequest);

        assertEquals(loginResponse.getEmail(), "test1@test.com");
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}