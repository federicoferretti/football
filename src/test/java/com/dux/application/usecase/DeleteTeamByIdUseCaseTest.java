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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteTeamByIdUseCaseTest {

    @Mock
    private TeamRepository teamRepositoryMock;
    @InjectMocks
    private DeleteTeamByIdUseCase deleteTeamByIdUseCase;

    @Test
    void testDeleteTeamSuccessfully() {
        Long idParam = 1L;

        Team teamSaved = new Team(1L, "Barcelona", "La Liga", "España");

        when(teamRepositoryMock.findById(idParam)).thenReturn(Optional.of(teamSaved));

        deleteTeamByIdUseCase.execute(idParam);

        verify(teamRepositoryMock, times(1)).findById(idParam);
        verify(teamRepositoryMock, times(1)).deleteById(idParam);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenDoesNotFoundTeam() {
        when(teamRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NotFoundException.class,
                () -> deleteTeamByIdUseCase.execute(anyLong())
        );

        assertEquals("Equipo no encontrado", exception.getMessage());
    }
}
