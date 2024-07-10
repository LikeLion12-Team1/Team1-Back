package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.dto.reportdto.ReportDto;
import com.magnetic.domain.crew.dto.reportdto.ReportRequestDto;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.entity.Report;
import com.magnetic.domain.crew.repository.PostRepository;
import com.magnetic.domain.crew.repository.ReportRepository;
import com.magnetic.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Builder
@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final PostRepository postRepository;
    private final ReportRepository reportRepository;

    public ReportDto reportPost(Long postId, User user, ReportRequestDto reportRequestDto){
        Post post = postRepository.findById(postId)//postId에 해당하는 post 엔티티 찾기
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        Report report = Report.builder()
                .reportReason(reportRequestDto.getReportReason())
                .reportedAt(LocalDate.now())
                .post(post)
                .user(user)
                .build();//신고 정보 가진 Report 엔티티 객체 생성

        Report savedReport = reportRepository.save(report);//생성된 Report 엔티티를 레포지토리로 데베에 저장

        return ReportDto.builder()
                .reportReason(savedReport.getReportReason())
                .postId(savedReport.getPost().getPostId())
                .userId(savedReport.getUser().getUserId())
                .reportedAt(savedReport.getReportedAt())
                .build();
    }

}
