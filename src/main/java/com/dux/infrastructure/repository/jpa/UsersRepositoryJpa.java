package com.dux.infrastructure.repository.jpa;

import com.dux.infrastructure.dao.UsersDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepositoryJpa extends JpaRepository<UsersDAO, Long> {

    Optional<UsersDAO> findByUsername(String username);
}
