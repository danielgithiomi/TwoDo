package com.danielgithiomi.twodo.mappers;

import com.danielgithiomi.twodo.domains.dtos.request.CreateUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.CreatedUserDto;
import com.danielgithiomi.twodo.domains.models.Role;
import com.danielgithiomi.twodo.domains.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(CreateUserDto createUserDto);

    @Mapping(target = "roles", expression = "java(mapRolesToList(user.getRoles()))")
    CreatedUserDto toResponseDto(User user);

    default List<String> mapRolesToList(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) return List.of();

        return roles.stream()
                .map(role -> "ROLE_" + role.getRole().getRoleName())
                .toList();
    }

}
