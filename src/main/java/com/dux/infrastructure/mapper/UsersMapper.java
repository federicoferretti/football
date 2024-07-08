package com.dux.infrastructure.mapper;

import com.dux.domain.entity.Users;
import com.dux.infrastructure.dao.UsersDAO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsersMapper {

    UsersDAO toDAO(Users users);

    Users toEntity(UsersDAO dao);
}
