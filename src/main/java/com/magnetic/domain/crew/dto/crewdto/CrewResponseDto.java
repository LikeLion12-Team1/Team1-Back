package com.magnetic.domain.crew.dto.crewdto;

import com.magnetic.domain.crew.entity.Crew;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CrewResponseDto {

    public Long crewId;

    public String name;

    public String region;

    public String sports_category;


    //Crew 객체를 받아서 CrewResponseDto 객체를 생성하고 반환
    public static CrewResponseDto from(Crew crew) {
        return CrewResponseDto.builder()
                .crewId(crew.getCrewId())
                .name(crew.getName())
                .region(crew.getRegion())
                .sports_category(crew.getSportsCategory())
                .build();
    }

}
