package com.magnetic.domain.crew.dto.response;

import com.magnetic.domain.crew.entity.Crew;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CrewResponseDto {

    public Long crewId;

    public String name;

    public String region;

    public String sports_category;



    public static CrewResponseDto from(Crew crew) {
        return CrewResponseDto.builder()
                .crewId(crew.getCrewId())
                .name(crew.getName())
                .region(crew.getRegion())
                .sports_category(crew.getSportsCategory())
                .build();
    }

}
