package com.danielgithiomi.twodo.domains.dtos.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record LoginResponse(
        String jwtToken,
        Date dateIssued
) {
}
