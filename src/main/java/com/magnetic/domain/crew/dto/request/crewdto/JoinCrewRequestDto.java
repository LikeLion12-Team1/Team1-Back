package com.magnetic.domain.crew.dto.request.crewdto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class JoinCrewRequestDto {

    public Long user_id;

    public Long crew_id;

    @Builder
    public JoinCrewRequestDto(Long user_id, Long crew_id){
        this.user_id = user_id;
        this.crew_id = crew_id;
    }
}
