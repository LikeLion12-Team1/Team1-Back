package com.magnetic.domain.crew.dto.request.crewdto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;


@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class UpdateCrewRequestDto {

    public String name;

    public String region;

    public String sports_category;

    @Builder
    public UpdateCrewRequestDto(String name, String region, String sports_category){
        this.name = name;
        this.region = region;
        this.sports_category = sports_category;
    }
}
