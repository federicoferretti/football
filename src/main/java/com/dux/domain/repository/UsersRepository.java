package com.dux.domain.repository;

import com.dux.domain.entity.Users;

import java.util.Optional;


public interface UsersRepository {

    Optional<Users> findByUsername(String username);
}
