package com.dux.infrastructure.repository;

import com.dux.domain.entity.Team;
import com.dux.domain.repository.TeamRepository;
import com.dux.infrastructure.dao.TeamDAO;
import com.dux.infrastructure.mapper.TeamMapper;
import com.dux.infrastructure.repository.jpa.TeamRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final TeamRepositoryJpa teamRepositoryJpa;
    private final TeamMapper mapper;


    @Override
    public List<Team> findAllTeams() {
        List<TeamDAO> teams = teamRepositoryJpa.findAll();

        return teams.stream().map(mapper::toEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Team> findById(Long id) {
        Optional<TeamDAO> team = teamRepositoryJpa.findById(id);

        return team.map(mapper::toEntity);
    }

    @Override
    public List<Team> findByName(String name) {
        List<TeamDAO> teams = teamRepositoryJpa.findByNameContaining(name);

        return teams.stream().map(mapper::toEntity).collect(Collectors.toList());
    }

    @Override
    public Team save(Team team) {
        TeamDAO dao = mapper.toDAO(team);

        TeamDAO teamSaved = teamRepositoryJpa.save(dao);

        return this.mapper.toEntity(teamSaved);
    }

    @Override
    public void deleteById(Long id) {
        teamRepositoryJpa.deleteById(id);
    }
}
