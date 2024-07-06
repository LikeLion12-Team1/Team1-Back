package com.magnetic.s3.repository;

import com.magnetic.s3.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
