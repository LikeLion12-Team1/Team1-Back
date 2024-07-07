package com.magnetic.domain.calendar.converter;

import com.magnetic.domain.calendar.dto.CalendarResponse;
import com.magnetic.domain.calendar.entity.Todo;

import java.util.List;

public class CalendarConverter {
    public static CalendarResponse.TodoPreviewListDto toTodoPreviewList(List<Todo> todoList) {
        List<CalendarResponse.TodoPreviewDto> todoPreviewDtoList = todoList.stream()
                .map(CalendarConverter::toTodoPreview).toList();

        return CalendarResponse.TodoPreviewListDto.builder()
                .todoPreviewDtoList(todoPreviewDtoList)
                .build();
    }

    public static CalendarResponse.TodoPreviewDto toTodoPreview(Todo todo) {
        return CalendarResponse.TodoPreviewDto.builder()
                .todoId(todo.getTodoId())
                .startAt(todo.getStartAt())
                .untilWhen(todo.getUntilWhen())
                .content(todo.getContent())
                .status(todo.getStatus())
                .category(todo.getCategory())
                .build();
    }
}
