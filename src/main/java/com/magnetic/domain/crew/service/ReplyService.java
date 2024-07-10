package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.dto.replydto.ReplyRequestDto;
import com.magnetic.domain.crew.dto.replydto.ReplyResponseDto;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.entity.Reply;
import com.magnetic.domain.crew.repository.PostRepository;
import com.magnetic.domain.crew.repository.ReplyRepository;
import com.magnetic.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor//DI 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyResponseDto createReply(Long postId, ReplyRequestDto replyRequestDto, User user){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        Reply reply = new Reply();
        reply.setContent(replyRequestDto.getContent());
        reply.setPost(post);

        Reply savedReply = replyRepository.save(reply);

        return ReplyResponseDto.from(post, user, savedReply);
    }


    public List<ReplyResponseDto> getReplies(Long postId, User user){
        //댓글 조회
        List<Reply> replies = replyRepository.findAllByPostId(postId);
        //엔티티->Dto
        List<ReplyResponseDto> replyResponseDtos = new ArrayList<ReplyResponseDto>();
        for (Reply reply : replies){
            ReplyResponseDto replyResponseDto = new ReplyResponseDto();
            replyResponseDto.setPostId(postId);
            replyResponseDto.setNickname(user.getNickname());
            replyResponseDto.setContent(reply.getContent());
            replyResponseDtos.add(replyResponseDto);
                    }
        //결과 반환
        return replyResponseDtos;
    }

}
