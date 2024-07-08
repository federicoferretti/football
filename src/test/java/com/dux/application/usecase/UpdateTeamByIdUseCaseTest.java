package com.dux.application.usecase;

import com.dux.application.exceptionhandler.exception.NotFoundException;
import com.dux.domain.entity.Team;
import com.dux.domain.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateTeamByIdUseCaseTest {

    @Mock
    private TeamRepository teamRepositoryMock;
    @InjectMocks
    private UpdateTeamByIdUseCase updateTeamByIdUseCase;

    @Test
    public void testUpdateTeamSuccessfully() {

        Long idParam = 1L;

        Team updatedTeam = new Team();
        updatedTeam.setName("Manchester City");
        updatedTeam.setLeague("Premier league");

        Team teamSaved = new Team(1L, "Barcelona", "La Liga", "España");

        Team newTeam = new Team(1L, "Manchester City", "Premier league", "España");


        when(teamRepositoryMock.findById(idParam)).thenReturn(Optional.of(teamSaved));
        when(teamRepositoryMock.save(newTeam)).thenReturn(newTeam);

        Team result = updateTeamByIdUseCase.execute(idParam, updatedTeam);

        assertEquals(newTeam.getId(), result.getId());
        assertEquals(newTeam.getName(), result.getName());
        assertEquals(newTeam.getLeague(), result.getLeague());
        assertEquals(newTeam.getCountry(), result.getCountry());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenDoesNotFoundTeam() {
        when(teamRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        Throwable exception = assertThrows(NotFoundException.class,
                () -> updateTeamByIdUseCase.execute(1L, new Team())
        );

        assertEquals("Equipo no encontrado", exception.getMessage());
    }
}
