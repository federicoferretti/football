package com.dux.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private Long id;
    private String name;
    private String league;
    private String country;
}
