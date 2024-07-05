package com.magnetic.domain.email.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EmailResponseDto(
        @JsonProperty("access_token")
        String accessToken
) {
}
