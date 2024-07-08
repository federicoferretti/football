package com.dux.application.usecase;

import com.dux.application.exceptionhandler.exception.CreateEntityException;
import com.dux.domain.entity.Team;
import com.dux.domain.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTeamUseCaseTest {

    @Mock
    private TeamRepository teamRepositoryMock;
    @InjectMocks
    private CreateTeamUseCase createTeamUseCase;


    @Test
    public void testCreateTeamSuccessfully() {
        Team param = new Team();
        param.setName("Barcelona");
        param.setLeague("La liga");
        param.setCountry("España");

        Team teamSaved = new Team(1L, "Barcelona", "La Liga", "España");

        when(teamRepositoryMock.save(param)).thenReturn(teamSaved);
        Team result = createTeamUseCase.execute(param);


        assertEquals(result, teamSaved);
    }

    @Test
    public void shouldThrowCreateEntityExceptionWhenFailsaved() {
        when(teamRepositoryMock.save(ArgumentMatchers.any(Team.class))).thenThrow(RuntimeException.class);

        Throwable exception = assertThrows(CreateEntityException.class,
                () -> createTeamUseCase.execute(ArgumentMatchers.any(Team.class))
        );

        assertEquals("La solicitud es inválida", exception.getMessage());
    }
}
