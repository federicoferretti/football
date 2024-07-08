package com.dux.application.request;

import com.dux.domain.entity.Team;
import lombok.Data;

@Data
public class TeamRequest {
    private String nombre;
    private String liga;
    private String pais;

    public Team toEntity() {
        Team team = new Team();
        team.setName(this.nombre);
        team.setLeague(this.liga);
        team.setCountry(this.pais);

        return team;
    }
}
