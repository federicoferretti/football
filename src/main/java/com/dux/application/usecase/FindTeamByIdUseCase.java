package com.dux.application.usecase;

import com.dux.application.exceptionhandler.exception.NotFoundException;
import com.dux.domain.entity.Team;
import com.dux.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class FindTeamByIdUseCase {

    private final TeamRepository teamRepository;

    public FindTeamByIdUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team execute(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipo no encontrado", 404));
    }
}
