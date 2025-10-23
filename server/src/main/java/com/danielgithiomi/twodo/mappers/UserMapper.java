package com.danielgithiomi.twodo.mappers;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.CreatedUserDto;
import com.danielgithiomi.twodo.domains.models.Role;
import com.danielgithiomi.twodo.domains.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserMapper {

    User toEntity(RegisterUserDto registerUserDto);

    // @Mapping(target = "roles", expression = "java(mapRolesToList(user.getRoles()))")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToList")
    CreatedUserDto toResponseDto(User user);

    @Named("mapRolesToList")
    default List<String> mapRolesToList(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) return List.of();

        return roles.stream()
                .map(role -> "ROLE_" + role.getRole().getRoleName())
                .toList();
    }

}
