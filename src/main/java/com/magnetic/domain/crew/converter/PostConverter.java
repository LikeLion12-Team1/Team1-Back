package com.magnetic.domain.crew.converter;

import com.magnetic.domain.crew.dto.postdto.PostResponseDto;
import com.magnetic.domain.crew.dto.postdto.QueryPostResponse;
import com.magnetic.domain.crew.entity.Post;

import java.util.List;

public class PostConverter {
    public static QueryPostResponse.PostPreviewListDto toPostResponseList(List<Post> crewList) {
        return QueryPostResponse.PostPreviewListDto
                .builder().build();
    }
}
