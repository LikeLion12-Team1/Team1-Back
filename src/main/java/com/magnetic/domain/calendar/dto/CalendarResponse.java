package com.magnetic.domain.calendar.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record CalendarResponse() {

    @Builder
    public record TodoPreviewListDto(
            List<TodoPreviewDto> todoPreviewDtoList
    ) {}

    @Builder
    public record TodoPreviewDto(
            Long todoId,
            LocalDateTime startAt,
            LocalDateTime untilWhen,
            String content,
            Byte status,
            String category
    ) {}
}
