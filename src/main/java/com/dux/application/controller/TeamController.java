package com.dux.application.controller;

import com.dux.application.exceptionhandler.ExceptionResponse;
import com.dux.application.request.TeamRequest;
import com.dux.application.response.TeamResponse;
import com.dux.application.usecase.CreateTeamUseCase;
import com.dux.application.usecase.DeleteTeamByIdUseCase;
import com.dux.application.usecase.FindAllTeamsUseCase;
import com.dux.application.usecase.FindTeamByIdUseCase;
import com.dux.application.usecase.FindTeamsByNameUseCase;
import com.dux.application.usecase.UpdateTeamByIdUseCase;
import com.dux.domain.entity.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class TeamController {

    private final FindAllTeamsUseCase findAllTeamsUseCase;
    private final FindTeamByIdUseCase findTeamByIdUseCase;
    private final FindTeamsByNameUseCase findTeamsByNameUseCase;
    private final CreateTeamUseCase createTeamUseCase;
    private final UpdateTeamByIdUseCase updateTeamUseCase;
    private final DeleteTeamByIdUseCase deleteTeamByIdUseCase;

    @Operation(summary = "Obtener todos los equipos", description = "Devuelve una lista de todos los equipos.")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = TeamResponse.class)))}
    )
    @GetMapping
    public ResponseEntity<List<TeamResponse>> findAllTeams() {
        List<Team> teams = findAllTeamsUseCase.execute();

        List<TeamResponse> teamsResponse = teams.stream().map(TeamResponse::from).toList();
        return new ResponseEntity<>(teamsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Buscar equipo por ID", description = "Devuelve un equipo basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeamResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<TeamResponse> findTeamById(@PathVariable Long id) {
        Team team = findTeamByIdUseCase.execute(id);

        return new ResponseEntity<>(TeamResponse.from(team), HttpStatus.OK);
    }

    @Operation(summary = "Buscar equipos por nombre", description = "Devuelve una lista de equipos que coinciden con el nombre proporcionado.")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = TeamResponse.class)))
    )
    @GetMapping(value = "/buscar")
    public ResponseEntity<List<TeamResponse>> findTeamsByName(@RequestParam(name = "nombre") String name) {
        List<Team> teams = findTeamsByNameUseCase.execute(name);

        List<TeamResponse> teamsResponse = teams.stream().map(TeamResponse::from).toList();
        return new ResponseEntity<>(teamsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo equipo", description = "Crea un nuevo equipo basado en los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class))),
            @ApiResponse(responseCode = "400", description = "La solicitud es inválida", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@Valid @RequestBody TeamRequest request) {
        Team team = createTeamUseCase.execute(request.toEntity());

        return new ResponseEntity<>(TeamResponse.from(team), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar equipo por ID", description = "Actualiza los datos de un equipo existente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamRequest request) {
        Team updatedTeam = updateTeamUseCase.execute(id, request.toEntity());
        return new ResponseEntity<>(TeamResponse.from(updatedTeam), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar equipo por ID", description = "Elimina un equipo existente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        deleteTeamByIdUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
