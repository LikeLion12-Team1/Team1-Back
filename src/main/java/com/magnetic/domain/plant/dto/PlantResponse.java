package com.magnetic.domain.plant.dto;

import lombok.Builder;

import java.util.List;

public record PlantResponse() {

    @Builder
    public record PlantPreviewListDto(
            List<PlantPreviewDto> plantPreviewDtoList
    ) {
    }

    @Builder
    public record PlantPreviewDto(
            String type,
            String name,
            Byte isLocked
    ) {
    }
}
