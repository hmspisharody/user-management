package com.drishticon.kencoreapp.model.repository;

import com.drishticon.kencoreapp.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    private void setup() {
        testEntityManager.flush();
        persistData();
    }

    private void persistData() {
        User user = User.builder().firstName("first-name").lastName("last-name")
                        .city("city")
                        .state("state")
                        .country("country")
                        .email("email")
                        .phoneNumber("12345678")
                        .zipCode("123123")
                        .password("some-pass").build();

        testEntityManager.persist(user);
    }

    @Test
    public void shouldReturnByPhoneNumber() {
        User response = repository.findByPhoneNumber("12345678");

        assertAll(
                () -> assertTrue(Objects.nonNull(response)),
                () -> assertEquals("email", response.getEmail()),
                () -> assertEquals("first-name", response.getFirstName())
        );
    }
}
