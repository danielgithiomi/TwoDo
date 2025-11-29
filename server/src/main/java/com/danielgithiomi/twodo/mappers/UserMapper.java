package com.danielgithiomi.twodo.mappers;

import com.danielgithiomi.twodo.domains.dtos.request.RegisterUserDto;
import com.danielgithiomi.twodo.domains.dtos.response.UserResponseDto;
import com.danielgithiomi.twodo.domains.models.Role;
import com.danielgithiomi.twodo.domains.models.User;
import com.danielgithiomi.twodo.security.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserMapper {

    User toEntity(RegisterUserDto registerUserDto);

    // @Mapping(target = "roles", expression = "java(mapRolesToList(user.getRoles()))")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToList")
    UserResponseDto toResponseDto(User user);

    @Named("mapRolesToList")
    default List<String> mapRolesToList(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) return List.of();

        return roles.stream()
                .map(role -> "ROLE_" + role.getRole().getRoleName())
                .toList();
    }

    default UserResponseDto fromDetailsToDto(UserDetails userDetails) {
        if (userDetails == null) {
            return null;
        }

        if (userDetails instanceof AuthUser authUser) {
            User user = authUser.user();
            // Map the User to UserResponseDto
            return UserResponseDto.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .roles(userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build();
        }

        // Fallback for other UserDetails implementations (e.g., Spring's default User)
        return UserResponseDto.builder()
                .username(userDetails.getUsername())
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .build();
    }

}
