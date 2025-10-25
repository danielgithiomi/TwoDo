package com.danielgithiomi.twodo.domains.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreatedUserDto {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
