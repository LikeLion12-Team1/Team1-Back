package com.magnetic.domain.calendar.service;

import com.magnetic.domain.calendar.converter.CalendarConverter;
import com.magnetic.domain.calendar.dto.CalendarRequest;
import com.magnetic.domain.calendar.dto.CalendarResponse;
import com.magnetic.domain.calendar.entity.Todo;
import com.magnetic.domain.calendar.repository.TodoRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.CalendarHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService {

    private final TodoRepository todoRepository;

    public CalendarResponse.TodoPreviewListDto getTodoList(User user) {
        List<Todo> todoList = todoRepository.findAllByUser(user);
        return CalendarConverter.toTodoPreviewList(todoList);
    }

    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CalendarHandler(ErrorStatus._NOT_FOUND_TODO));
        todoRepository.delete(todo);
    }

    public CalendarResponse.TodoPreviewDto changeTodoStatus(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CalendarHandler(ErrorStatus._NOT_FOUND_TODO));
        if (todo.getStatus() == 0) {
            todo.changeStatus((byte) 1);
        } else {
            todo.changeStatus((byte) 0);
        }
        return CalendarConverter.toTodoPreview(todoRepository.save(todo));
    }

    public CalendarResponse.TodoPreviewDto updateTodo(Long todoId, CalendarRequest.PostTodoDto request) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CalendarHandler(ErrorStatus._NOT_FOUND_TODO));
        todo.update(request);
        return CalendarConverter.toTodoPreview(todoRepository.save(todo));
    }

    public CalendarResponse.TodoPreviewDto addTodo(User user, CalendarRequest.PostTodoDto request) {
        Todo newTodo = Todo.builder()
                .user(user)
                .startAt(request.startAt())
                .untilWhen(request.untilWhen())
                .content(request.content())
                .category(request.category())
                .status((byte) 0)
                .build();
        return CalendarConverter.toTodoPreview(todoRepository.save(newTodo));
    }
}
