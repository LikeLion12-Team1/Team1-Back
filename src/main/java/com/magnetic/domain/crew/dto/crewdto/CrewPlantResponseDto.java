package com.magnetic.domain.crew.dto.crewdto;

import lombok.Builder;

@Builder
public record CrewPlantResponseDto(
        String crewMemberName,
        Long crewMemberMainPlantId
) {
}
