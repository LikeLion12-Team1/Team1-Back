package com.magnetic.domain.crew.entity;


import com.magnetic.domain.crew.dto.request.crewdto.UpdateCrewRequestDto;
import com.magnetic.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
//@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.


public class Crew extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crew_id;

    @Column
    private String name;
    @Column
    private String region;
    @Column
    private String sports_category;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)
    private List<CrewPost> crew_posts;

    //회원크루 연결
    //@OneToMany(mappedBy = "crew", cascade = CascadeType.ALL) // 크루 삭제된다고 회원도 삭제되면 안 되는데 그럼 CascadeType.ALL 이걸 바꿔야할 듯
    //private List<UserCrew> usercrew;


    @Builder
    public Crew(Long crew_id, String name, String region, String sports_category, List<CrewPost> crew_posts){
        this.crew_id = crew_id;
        this.name = name;
        this.region = region;
        this.sports_category = sports_category;
        this.crew_posts = crew_posts;
    }

    public void update(UpdateCrewRequestDto updateCrewRequestDto) {
        name = updateCrewRequestDto.getName();
        region = updateCrewRequestDto.getRegion();
        sports_category = updateCrewRequestDto.getSports_category();
    }



}

