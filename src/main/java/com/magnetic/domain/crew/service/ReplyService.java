package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.dto.replydto.ReplyRequestDto;
import com.magnetic.domain.crew.dto.replydto.ReplyResponseDto;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.entity.Reply;
import com.magnetic.domain.crew.repository.PostRepository;
import com.magnetic.domain.crew.repository.ReplyRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.PostHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor//DI 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyResponseDto createReply(Long postId, ReplyRequestDto replyRequestDto, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));

        Reply reply = Reply.builder()
                .content(replyRequestDto.getContent())
                .user(user)
                .post(post)
                .build();
        Reply savedReply = replyRepository.save(reply);

        return ReplyResponseDto.builder()
                .content(savedReply.getContent())
                .createdAt(savedReply.getCreatedAt())
                .build();
    }
}
