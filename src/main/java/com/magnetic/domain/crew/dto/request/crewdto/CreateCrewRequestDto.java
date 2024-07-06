package com.magnetic.domain.crew.dto.request.crewdto;

import com.magnetic.domain.crew.entity.Crew;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class CreateCrewRequestDto {
    //크루 이름
    public String name;
    //크루 지역
    public String region;
    //크루 스포츠 카테고리
    public String sports_category;

    @Builder
    public CreateCrewRequestDto(String name, String region, String sports_category){
        this.name = name;
        this.region = region;
        this.sports_category = sports_category;
    }


    public Crew toEntity() {
        return Crew.builder()
                .name(name)
                .region(region)
                .sports_category(sports_category)
                .build();
    }



}

