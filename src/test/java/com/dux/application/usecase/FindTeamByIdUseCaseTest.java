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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindTeamByIdUseCaseTest {

    @Mock
    private TeamRepository teamRepositoryMock;
    @InjectMocks
    private FindTeamByIdUseCase findTeamByIdUseCase;

    @Test
    public void testUpdateTeamSuccessfully() {
        Long idParam = 1L;

        Team teamSaved = new Team(1L, "Barcelona", "La Liga", "España");

        when(teamRepositoryMock.findById(idParam)).thenReturn(Optional.of(teamSaved));

        findTeamByIdUseCase.execute(idParam);

        verify(teamRepositoryMock, times(1)).findById(idParam);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenDoesNotFoundTeam() {
        when(teamRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NotFoundException.class,
                () -> findTeamByIdUseCase.execute(anyLong())
        );

        assertEquals("Equipo no encontrado", exception.getMessage());
    }
}
