package com.dux.application.usecase;

import com.dux.application.exceptionhandler.exception.CreateEntityException;
import com.dux.domain.entity.Team;
import com.dux.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTeamUseCase {

    private final TeamRepository teamRepository;

    public CreateTeamUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team execute(Team team) {
        try {
            return teamRepository.save(team);
        } catch (Exception e) {
            throw new CreateEntityException("La solicitud es inválida", 400);
        }
    }
}
