package com.magnetic.domain.crew.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CrewResponseDto {

    public Long crew_id;

    public String name;

    public String region;

    public String sports_category;


    public static CrewResponseDto from(Crew crew) {
        return CrewResponseDto.builder()
                .crew_id(crew.getCrew_id())
                .name(crew.getName())
                .region(crew.getRegion())
                .sports_category(crew.getSports_category())
                .build();
    }

}
