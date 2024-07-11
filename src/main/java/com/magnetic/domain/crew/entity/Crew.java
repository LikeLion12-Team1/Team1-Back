package com.magnetic.domain.crew.entity;

import com.magnetic.domain.crew.dto.crewdto.UpdateCrewRequestDto;
import com.magnetic.domain.user.entity.UserCrew;
import com.magnetic.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity//데이터베이스와 연결되는 Entity 클래스
@Getter//모든 메서드에 대한 Getter 메서드 자동 생성
@Builder//Builder패턴을 사용할 수 있게 해줌 -> 객체 생성시 필드 값 유연하게 설정 가능
@NoArgsConstructor//매개변수 없는 생성자 자동 생성
@AllArgsConstructor//모든 필드를 매개변수로 갖는 생성자 자동 생성
public class Crew {

    @Id//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//crewId 필드값이 자동생성되도록 설정
    @Column(name = "crew_id")//crewId필드값이 테이블의 crew_Id열에 매핑됨
    private Long crewId;

    private String name;
    private String region;
    private String sportsCategory;

    private Long crewCount;
    private LocalDate createdAt;
    private LocalDate joinedAt;
    private String crewImg;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)//일대다 관계 //CascadeType.ALL-> crew 상태변화가 관련 엔티티에도 영향
    private List<UserCrew> userCrewList = new ArrayList<>();//userCrewList필드는 UserCrew의 엔티티 목록 저장

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)
    private List<CrewPost> crewPostList = new ArrayList<>();//crewPostList 필드는 Crew와 관련된 CrewPost 엔티티의 목록을 저장

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)
    private List<CrewChallenge> crewChallengeList = new ArrayList<>();//crewChallengeList 필드는 Crew와 관련된 CrewChallenge 엔티티의 목록을 저장

    //UpdateCrewRequestDto 객체를 사용하여 Crew 엔티티의 name, region, sportsCategory 필드를 업데이트
    public void update(UpdateCrewRequestDto updateCrewRequestDto) {
        name = updateCrewRequestDto.getName();
        region = updateCrewRequestDto.getRegion();
        sportsCategory = updateCrewRequestDto.getSportsCategory();
    }

    public void increaseMemberCount() {
        crewCount++;
    }

    public void setImage(String newImageUrl) {
        crewImg = newImageUrl;
    }
}
