package com.dux.application.usecase;

import com.dux.application.exceptionhandler.exception.NotFoundException;
import com.dux.domain.entity.Team;
import com.dux.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTeamByIdUseCase {

    private final TeamRepository teamRepository;

    public UpdateTeamByIdUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team execute(Long id, Team updatedTeam) {
        Team savedTeam = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipo no encontrado", 404));

        updateTeamDetails(savedTeam, updatedTeam);

        return teamRepository.save(savedTeam);
    }

    private void updateTeamDetails(Team existingTeam, Team updatedTeam) {
        if (updatedTeam.getName() != null) {
            existingTeam.setName(updatedTeam.getName());
        }
        if (updatedTeam.getLeague() != null) {
            existingTeam.setLeague(updatedTeam.getLeague());
        }
        if (updatedTeam.getCountry() != null) {
            existingTeam.setCountry(updatedTeam.getCountry());
        }
    }
}
