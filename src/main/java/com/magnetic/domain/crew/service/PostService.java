package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.dto.postdto.CreatePostRequestDto;
import com.magnetic.domain.crew.dto.postdto.UpdatePostRequestDto;
import com.magnetic.domain.crew.dto.postdto.PostResponseDto;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.repository.CrewPostRepository;
import com.magnetic.domain.crew.repository.PostRepository;
import com.magnetic.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor//DI 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
@Transactional

public class PostService {

    private final PostRepository postRepository;
    private final CrewPostRepository crewPostRepository;

    //게시글 생성
    //CreatePostRequestDto에 post(postType, content, photoUrl) + user(nickname) 포함
    public PostResponseDto createPost(CreatePostRequestDto createPostRequestDto, User user) {
        Post post = createPostRequestDto.toEntity(); //RequestDto로 받은 정보 엔티로 바꿔서 post에 저장
        Post savedPost = postRepository.save(post);
        return PostResponseDto.from(savedPost, user);
    }


    //게시글 상세 조회
    public PostResponseDto getPost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return PostResponseDto.from(post, user);
    }


//    //게시글 목록 조회
//    public List<PostResponseDto> getPostsByCrewName(String crewName, User user) {
//        List<Post> posts = crewPostRepository.findAllPostByCrewName(crewName, user);
//        return posts.stream()
//                .map(PostResponseDto::from)
//                .collect(Collectors.toList());
//    }

    //게시글 타입별 조회도 만들어야합니댜...


    //게시글 수정
    public PostResponseDto updatePost(Long postId, UpdatePostRequestDto updatePostRequestDto, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.update(updatePostRequestDto);

        Post updatedPost = postRepository.save(post);
        return PostResponseDto.from(updatedPost, user);
    }


    //게시글 삭제
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        postRepository.delete(post);
    }
}
