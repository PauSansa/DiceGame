package com.sansa.dicegame.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByName(String name);
    Optional<User> findByName(String name);
    Optional<User> findByUsername(String username);
}
