package org.example.repository;

import org.example.entity.User;

public interface    UserRepository {
    User findByUsername(String username);
}

