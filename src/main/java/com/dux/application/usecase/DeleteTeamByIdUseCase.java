package com.dux.application.usecase;

import com.dux.application.exceptionhandler.exception.NotFoundException;
import com.dux.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTeamByIdUseCase {

    private final TeamRepository teamRepository;

    public DeleteTeamByIdUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void execute(Long id) {
        teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipo no encontrado", 404));

        teamRepository.deleteById(id);
    }
}
