package com.mkosturkov.orgmetadata.user;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final Map<Integer, User> USERS = Map.of(
            1, new User(1, "testEmail1@email.com", "0888999777"),
            2, new User(2, "testEmail2@email.com", "0788999777"),
            3, new User(3, "testEmail3@email.com", "0688999777"));

    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(USERS.get(id));
    }
}
