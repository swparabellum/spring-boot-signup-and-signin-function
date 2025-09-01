package com.example.signinup.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    // 시작 날짜와 종료 날짜 사이의 모든 이벤트를 찾습니다.
    List<Event> findAllByStartBetween(LocalDate start, LocalDate end);
}