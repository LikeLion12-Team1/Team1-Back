package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
