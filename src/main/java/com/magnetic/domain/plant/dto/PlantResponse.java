package com.magnetic.domain.plant.dto;

import lombok.Builder;

import java.util.List;

public record PlantResponse() {

    @Builder
    public record PlantPreviewListDto(
            Long holdingTokens,
            List<PlantPreviewDto> plantPreviewDtoList,
            Long userCount
    ) {
    }

    @Builder
    public record PlantPreviewDto(
            Long plantId
    ) {
    }
}
