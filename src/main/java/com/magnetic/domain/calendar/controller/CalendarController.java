package com.magnetic.domain.calendar.controller;

import com.magnetic.domain.calendar.dto.CalendarRequest;
import com.magnetic.domain.calendar.dto.CalendarResponse;
import com.magnetic.domain.calendar.service.CalendarService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Operation(summary = "캘린더 조회", description = "일정 목록 조회")
    @GetMapping("")
    public ApiResponse<CalendarResponse.TodoPreviewListDto> getTodoList(
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(calendarService.getTodoList(user));
    }

    @Operation(summary = "캘린더에 일정 추가", description = "일정 추가")
    @PostMapping("")
    public ApiResponse<CalendarResponse.TodoPreviewDto> addTodo(
            @AuthenticationPrincipal User user,
            @RequestBody CalendarRequest.PostTodoDto request
            ) {
        return ApiResponse.onSuccess(calendarService.addTodo(user, request));
    }

    @Operation(summary = "캘린더 일정 수정", description = "일정 수정")
    @PostMapping("/{todo_id}")
    public ApiResponse<CalendarResponse.TodoPreviewDto> updateTodo(
            @RequestBody CalendarRequest.PostTodoDto request,
            @PathVariable(name = "todo_id") Long todoId
    ) {
        return ApiResponse.onSuccess(calendarService.updateTodo(todoId, request));
    }

    @Operation(summary = "캘린더 일정 삭제", description = "일정 삭제")
    @DeleteMapping("/{todo_id}")
    public ApiResponse<?> deleteTodo(
            @PathVariable(name = "todo_id") Long todoId
    ) {
        calendarService.deleteTodo(todoId);
        return ApiResponse.noContent();
    }

    @Operation(summary = "캘린더 일정 수행 여부 수정(체크)",
            description = "수행 완료 상태의 일정에 요청 들어오면 수행 미완료로 변경, 수행 미완료 상태의 일정에 요청 들어오면 수행 완료로 변경")
    @PostMapping("/{todo_id}/is-done")
    public ApiResponse<CalendarResponse.TodoPreviewDto> changeTodoStatus(
            @PathVariable(name = "todo_id") Long todoId
    ) {
        return ApiResponse.onSuccess(calendarService.changeTodoStatus(todoId));
    }
}
