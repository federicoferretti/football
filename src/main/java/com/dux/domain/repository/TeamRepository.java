package com.dux.domain.repository;

import com.dux.domain.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository {

    List<Team> findAllTeams();

    Optional<Team> findById(Long id);

    List<Team> findByName(String name);

    Team save(Team team);

    void deleteById(Long id);
}
