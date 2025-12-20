package controller;

import service.EventService;
import model.Event;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // DELETE event by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEventById(@PathVariable int id) {

        boolean isDeleted = eventService.deleteEvent(id);

        if (!isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Event with ID " + id + " not found");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Event with ID " + id + " deleted successfully");
    }
}
