package com.magnetic.domain.crew.service;

import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.crew.converter.CrewConverter;
import com.magnetic.domain.crew.dto.crewdto.*;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.repository.CrewChallengeRepository;
import com.magnetic.domain.crew.repository.CrewRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserCrew;
import com.magnetic.domain.user.repository.UserCrewRepository;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.CrewHandler;
import com.magnetic.s3.S3Manager;
import com.magnetic.s3.entity.Uuid;
import com.magnetic.s3.repository.UuidRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor//DI 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
@Transactional//메서드 실행 시 트랜잭션 처리를 자동으로 수행
public class CrewService {

    private final CrewRepository crewRepository;//크루 관련 데이터 액세스를 위한 리포지토리 인터페이스를 주입
    private final UserCrewRepository userCrewRepository;
    private final CrewChallengeRepository crewChallengeRepository;
    private final UuidRepository uuidRepository;
    private final S3Manager s3Manager;

    // 크루 생성
    public CrewResponseDto createCrew(CreateCrewRequestDto createCrewRequestDto, MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String url = s3Manager.uploadFile(s3Manager.generateImage(savedUuid), file);

        Crew crew = createCrewRequestDto.toEntity();//CreateCrewRequestDto 객체에서 Crew 객체를 생성
        crew.setImage(url);

        Crew savedCrew = crewRepository.save(crew);//crewRepository에 crew 객체를 저장하고, 저장된 객체를 savedCrew에 할당
        return CrewResponseDto.from(savedCrew);//Crew 객체를 CrewResponseDto 객체로 변환하여 반환
    }

    // 크루 목록 조회
    public List<CrewResponseDto> getAllCrewsByOptions(String region, String category) {
        return crewRepository.findAllCrew(region, category);
    }

    //크루 지역별 조회
    public List<CrewResponseDto> getCrewsByRegion(String region) {
        List<Crew> crews = crewRepository.findByRegion(region);
        return crews.stream()
                .map(CrewResponseDto::from)
                .collect(Collectors.toList());
    }

    //크루 스포츠 카테고리별 조회
    public List<CrewResponseDto> getCrewsBySportsCategory(String sportsCategory) {
        List<Crew> crews = crewRepository.findBySportsCategory(sportsCategory);
        return crews.stream()
                .map(CrewResponseDto::from)
                .collect(Collectors.toList());
    }

    // 크루 수정
    //crewId와 updateCrewRequestDto를 입력받아 크루 정보를 수정
    public CrewResponseDto updateCrew(Long crewId, UpdateCrewRequestDto updateCrewRequestDto) {
        Crew crew = crewRepository.findById(crewId)//crewId에 해당하는 크루 엔티티 정보 찾아서 crew에 저장
                .orElseThrow(() -> new IllegalArgumentException("해당 크루가 존재하지 않습니다."));

        crew.update(updateCrewRequestDto);
        //crew 객체의 update 메서드를 호출하여 updateCrewRequestDto에 담긴 정보로 크루 정보를 업데이트

        Crew updatedCrew = crewRepository.save(crew); //업데이트된 crew 객체를 crewRepository에 저장하고, 그 결과를 updatedCrew 변수에 저장
        return CrewResponseDto.from(updatedCrew);//업데이트된 크루 정보를 CrewResponseDto로 변환하여 반환
    }

    // 크루 삭제
    public void deleteCrew(Long crewId) {
        Crew crew = crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 크루가 존재하지 않습니다."));

        crewRepository.delete(crew);//crewRepository를 사용하여 찾은 크루 엔티티를 삭제
    }

    public boolean crewNameDuplicate(String crewName) {
        return crewRepository.existsByName(crewName);
    }

    //크루 가입
    public JoinCrewDto joinCrew(Long crewId, User user){
        Crew crew = crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 크루가 존재하지 않습니다."));

        Optional<UserCrew> existingUserCrew = userCrewRepository.findByUserIdAndCrewId(user.getUserId(), crewId);
        if (existingUserCrew.isPresent()) {
            throw new IllegalArgumentException("이미 해당 크루에 가입되어 있습니다.");
        }

        crew.increaseMemberCount();
        crewRepository.save(crew);

        UserCrew userCrew = UserCrew.builder()
                .user(user)
                .crew(crew)
                .joinedAt(LocalDate.now())
                .status("active")
                .build();

        userCrewRepository.save(userCrew);

        return JoinCrewDto.builder()
                .userId(user.getUserId())
                .crewId(crew.getCrewId())
                .crewCount(crew.getCrewCount())
                .joinedAt(userCrew.getJoinedAt())
                .build();
    }

    // 특정 크루 조회
    public CrewCustomResponse.Preview getCrew(Long crewId) {
        Crew crew = crewRepository.findById(crewId)
                .orElseThrow(() -> new CrewHandler(ErrorStatus._NOT_FOUND_CREW));
        Long memberCount = userCrewRepository.countAllByCrew(crew);
        List<Challenge> challengeList = crewChallengeRepository.findAllChallengeByCrew(crew);
        List<CrewCustomResponse.CrewChallengePreview> crewChallengePreviewList = challengeList.stream()
                .map(CrewConverter::toChallengePreview).toList();

        return CrewCustomResponse.Preview.builder()
                .crewImg(crew.getCrewImg())
                .crewName(crew.getName())
                .memberCount(memberCount)
                .crewChallengePreviewList(crewChallengePreviewList)
                .crewStartAt(crew.getCreatedAt())
                .build();
    }
}

    
