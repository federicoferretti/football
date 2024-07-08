package com.dux.application.usecase;


import com.dux.domain.entity.Team;
import com.dux.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllTeamsUseCase {

    private final TeamRepository teamRepository;

    public FindAllTeamsUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> execute() {
        return teamRepository.findAllTeams();
    }
}
