package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@SpringBootTest(classes = BookstoreApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void findByUsernameReturnsSavedAdmin() {
        User admin = repository.findByUsername("admin");
        assertThat(admin).isNotNull();
        assertThat(admin.getEmail()).isEqualTo("admin@bookstore.com");
        assertThat(admin.getRole()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    public void findByUsernameReturnsSavedUser() {
        User user = repository.findByUsername("user");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("user@bookstore.com");
        assertThat(user.getRole()).isEqualTo("ROLE_USER");
    }

    @Test
    public void createNewUser() {
        String username = "repoTestUser_1";
        User user = new User(
                username,
                passwordEncoder.encode("secret"),
                "repotest@bookstore.com",
                "ROLE_USER");
        repository.save(user);
        assertThat(user.getId()).isNotNull();

        User loaded = repository.findByUsername(username);
        assertThat(loaded).isNotNull();
        assertThat(loaded.getEmail()).isEqualTo("repotest@bookstore.com");
    }

    @Test
    public void deleteUser() {
        String username = "userToDelete_1";
        User user = new User(
                username,
                passwordEncoder.encode("secret"),
                "delete@bookstore.com",
                "ROLE_USER");
        repository.save(user);
        Long id = user.getId();
        assertThat(repository.findById(id)).isPresent();

        repository.deleteById(id);

        assertThat(repository.findById(id)).isEmpty();
        assertThat(repository.findByUsername(username)).isNull();
    }
}
