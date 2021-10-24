package com.aeserver.controller;

import com.aeserver.model.AEvent;
import com.aeserver.repository.AEventsRepository;
import com.aeserver.repository.AEventsRepositoryMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AEventsController {

  @Autowired
  AEventsRepositoryMock eventRepo;

  @GetMapping("/event/{id}")
  public ResponseEntity<List<AEvent>> findById(id) {
    List<AEvent> event = eventRepo.findById();
    return new ResponseEntity<>(event, HttpStatus.OK);
  }

  @PostMapping("/event")
  public ResponseEntity<List<AEvent>> save(AEvent) {
    if (AEvent.id == 0){
      eventRepo.save(AEvent)
    }
    eventRepo.save(AEvent)

  }

  @GetMapping("/event")
  public ResponseEntity<List<AEvent>> getEvents() {
    List<AEvent> events = eventRepo.findAll();
    return new ResponseEntity<>(events, HttpStatus.OK);
  }
}
