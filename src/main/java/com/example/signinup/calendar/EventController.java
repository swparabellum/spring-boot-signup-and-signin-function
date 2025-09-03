package com.example.signinup.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    @GetMapping("/cal")
    public String calendarPage() {
        return "cal";
    }


    @GetMapping("/api/events")
    @ResponseBody
    public List<Event> getEvents(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return eventRepository.findAllByStartBetween(start, end);
    }

    /**
     * 프론트엔드에서 보낸 새로운 이벤트를 저장합니다.
     * JSON 데이터 {"title": "...", "start": "...", "end": "..."}가
     * @RequestBody를 통해 Event 객체로 자동 매핑됩니다.
     */
//    @PostMapping("/api/events")
//    @ResponseBody
//    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
//        // start와 end가 포함된 event 객체를 그대로 저장합니다.
//        Event savedEvent = eventRepository.save(event);
//        return ResponseEntity.ok(savedEvent);
//    }

    @CrossOrigin
    @PostMapping("/api/events")
    @ResponseBody
    public ResponseEntity<Event> addEvent(@RequestParam String  title, @RequestParam String start, @RequestParam String end) {

        Event event = Event.builder()
                .title(title)
                .start(LocalDate.parse(start))
                .end(LocalDate.parse(end))
                .build();
        // start와 end가 포함된 event 객체를 그대로 저장합니다.
        Event savedEvent = eventRepository.save(event);
        return ResponseEntity.ok(savedEvent);
    }
}