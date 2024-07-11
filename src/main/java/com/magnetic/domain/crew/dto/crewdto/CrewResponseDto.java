package com.magnetic.domain.crew.dto.crewdto;

import com.magnetic.domain.crew.entity.Crew;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrewResponseDto {

    private String photoUrl;

    private Long crewId;

    private String name;

    private String region;

    private String sportsCategory;

    private LocalDate createdAt;

    //Crew 객체를 받아서 CrewResponseDto 객체를 생성하고 반환
    public static CrewResponseDto from(Crew crew) {
        return CrewResponseDto.builder()
                .photoUrl(crew.getCrewImg())
                .crewId(crew.getCrewId())
                .name(crew.getName())
                .region(crew.getRegion())
                .createdAt(crew.getCreatedAt())
                .sportsCategory(crew.getSportsCategory())
                .build();
    }

}
