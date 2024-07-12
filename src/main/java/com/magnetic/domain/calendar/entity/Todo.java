package com.magnetic.domain.calendar.entity;

import com.magnetic.domain.calendar.dto.CalendarRequest;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long todoId;

    private String content;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT default 0")
    private Byte status;
    private String category;

    private LocalDateTime startAt;
    private LocalDateTime untilWhen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(CalendarRequest.PostTodoDto request) {
        content = request.content();
        category = request.category();
        startAt = request.startAt();
        untilWhen = request.untilWhen();
    }

    public void changeStatus(Byte newStatus) {
        status = newStatus;
    }
}
