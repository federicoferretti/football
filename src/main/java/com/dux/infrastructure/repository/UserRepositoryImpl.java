package com.dux.infrastructure.repository;

import com.dux.domain.entity.Users;
import com.dux.domain.repository.UsersRepository;
import com.dux.infrastructure.dao.UsersDAO;
import com.dux.infrastructure.mapper.UsersMapper;
import com.dux.infrastructure.repository.jpa.UsersRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UsersRepository {

    private final UsersRepositoryJpa usersRepositoryJpa;
    private final UsersMapper mapper;


    @Override
    public Optional<Users> findByUsername(String username) {
        Optional<UsersDAO> dao = usersRepositoryJpa.findByUsername(username);

        return dao.map(this.mapper::toEntity);
    }
}
