package com.magnetic.domain.calendar.repository;

import com.magnetic.domain.calendar.entity.Todo;
import com.magnetic.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}
