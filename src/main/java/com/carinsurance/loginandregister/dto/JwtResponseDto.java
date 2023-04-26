package com.carinsurance.loginandregister.dto;

import lombok.Builder;

@Builder
public record JwtResponseDto( String username,
                              String token) {
}
