package com.magnetic.domain.crew.dto.crewdto;

import com.magnetic.domain.crew.entity.Crew;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter//Getter 메서드를 자동으로 생성-> 이를 통해 필드의 값을 가져올 수 있음

public class CreateCrewRequestDto {
    //크루 이름
    public String name;
    //크루 지역
    public String region;
    //크루 스포츠 카테고리
    public String sportsCategory;

    //private LocalDate createdAt;

    @Builder//Builder 패턴을 사용하여 객체를 생성
    public CreateCrewRequestDto(String name, String region, String sportsCategory, LocalDate createdAt){
        this.name = name;
        this.region = region;
        this.sportsCategory = sportsCategory;
        //this.createdAt = createdAt;
    }


    //CreateCrewRequestDto 객체를 Crew 엔티티로 변환
    public Crew toEntity() {
        return Crew.builder()
                .name(name)
                .region(region)
                .sportsCategory(sportsCategory)
                .build();
    }



}

