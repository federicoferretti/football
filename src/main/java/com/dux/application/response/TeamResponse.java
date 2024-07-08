package com.dux.application.response;

import com.dux.domain.entity.Team;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponse {
    private Long id;
    private String nombre;
    private String liga;
    private String pais;

    public static TeamResponse from(Team team) {

        return TeamResponse.builder()
                .id(team.getId())
                .nombre(team.getName())
                .liga(team.getLeague())
                .pais(team.getCountry())
                .build();
    }
}
