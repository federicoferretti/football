package com.dux.infrastructure.mapper;

import com.dux.domain.entity.Team;
import com.dux.infrastructure.dao.TeamDAO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeamMapper {

    TeamDAO toDAO(Team team);

    Team toEntity(TeamDAO dao);
}
