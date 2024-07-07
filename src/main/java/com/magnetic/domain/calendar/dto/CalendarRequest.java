package com.magnetic.domain.calendar.dto;

import java.time.LocalDateTime;

public record CalendarRequest() {
    public record PostTodoDto(
        String content,
        String category,
        LocalDateTime startAt,
        LocalDateTime untilWhen
    ) {}
}
