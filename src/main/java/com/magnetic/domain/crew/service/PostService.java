package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.converter.PostConverter;
import com.magnetic.domain.crew.dto.postdto.CreatePostRequestDto;
import com.magnetic.domain.crew.dto.postdto.QueryPostResponse;
import com.magnetic.domain.crew.dto.postdto.UpdatePostRequestDto;
import com.magnetic.domain.crew.dto.postdto.PostResponseDto;
import com.magnetic.domain.crew.dto.replydto.ReplyResponseDto;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.entity.Reply;
import com.magnetic.domain.crew.repository.CrewRepository;
import com.magnetic.domain.crew.repository.PostRepository;
import com.magnetic.domain.crew.repository.ReplyRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.CrewHandler;
import com.magnetic.global.common.exception.handler.PostHandler;
import com.magnetic.s3.S3Manager;
import com.magnetic.s3.entity.Uuid;
import com.magnetic.s3.repository.UuidRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor//DI 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CrewRepository crewRepository;
    private final ReplyRepository replyRepository;
    private final UuidRepository uuidRepository;
    private final S3Manager s3Manager;

    //게시글 생성
    public Long createPost(CreatePostRequestDto createPostRequestDto, User user) {
        Post post = Post.builder()
                .user(user)
                .content(createPostRequestDto.getContent())
                .category(createPostRequestDto.getCategory())
                .build();
        Post savedPost = postRepository.save(post);

        return savedPost.getPostId();
    }

    // 게시글 사진 업로드
    public QueryPostResponse.CreatedPostResponse uploadPostImage(Long postId, MultipartFile file, User user) {
        String url = null;
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));

        if (file != null && !file.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            Uuid savedUuid = uuidRepository.save(Uuid.builder()
                    .uuid(uuid).build());

            url = s3Manager.uploadFile(s3Manager.generateImage(savedUuid), file);

            post.uploadImage(url);
        }
        return QueryPostResponse.CreatedPostResponse.builder()
                .nickname(user.getNickname())
                .postImg(url)
                .postCreatedAt(post.getCreatedAt())
                .build();
    }

    // 게시글 상세 조회
    public PostResponseDto getPost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        List<Reply> replies = replyRepository.findAllByPostId(postId);
        List<ReplyResponseDto> replyResponseDtoList = replies.stream()
                .map(PostConverter::toReplyResponseDto).toList();

        return PostResponseDto.builder()
                .postId(postId)
                .category(post.getCategory())
                .nickname(user.getNickname())
                .content(post.getContent())
                .photoUrl(post.getPhotoUrl())
                .replies(replyResponseDtoList)
                .build();
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

    public QueryPostResponse.PostPreviewListDto getPostList(String crewName) {
        Crew crew = crewRepository.findByName(crewName)
                .orElseThrow(() -> new CrewHandler(ErrorStatus._NOT_FOUND_CREW));
        List<QueryPostResponse.PostPreviewDto> postPreviewDtoList = crewRepository.findAllPost(crew);

        return QueryPostResponse.PostPreviewListDto.builder()
                .postPreviewList(postPreviewDtoList)
                .build();
    }

    //게시글 신고
    public boolean reportPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (post != null) {
            post.reportPost();
            postRepository.save(post);
            return true;
        }
        return false;
    }

}

