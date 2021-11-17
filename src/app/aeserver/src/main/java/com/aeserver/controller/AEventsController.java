package com.aeserver.controller;

import com.aeserver.exception.PreConditionalFailedException;
import com.aeserver.exception.ResourceNotFoundException;
import com.aeserver.model.AEvent;
import com.aeserver.repository.AEventsRepository;
import com.aeserver.repository.AEventsRepositoryMock;
import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.List;

@CrossOrigin(origins = "*") //this line
@RestController
@RequestMapping("/api")
public class AEventsController {

  @Autowired
  AEventsRepository eventRepo;


  @GetMapping("/aevent")
  public ResponseEntity<List<AEvent>> getEvents() {
    List<AEvent> events = eventRepo.findAll();
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @GetMapping("/aevent/summary")
  @JsonView(Views.Summary.class)
  public ResponseEntity<List<AEvent>> getEventSummary() {


    List<AEvent> events = eventRepo.findAll();
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @GetMapping("/aevent/{id}")
  public ResponseEntity<AEvent> getEventById(@PathVariable Long id) {
    AEvent events = eventRepo.findById(id);
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @PostMapping("/aevent")
  public ResponseEntity<AEvent> saveEvents(@RequestBody AEvent event) {
    AEvent events = eventRepo.save(event);
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @PutMapping("/aevent/{id}")
  @ResponseBody
  public ResponseEntity<AEvent> putEventById(@PathVariable Long id, @RequestBody AEvent event) {

    System.out.println(event.getDescription());


    if (event.getId() != id)
      throw new PreConditionalFailedException("AEvent-Id=" + event.getId() + " does not match path parameter=" + id);

    AEvent events = eventRepo.save(event);
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @DeleteMapping("/aevent/{id}")
  public ResponseEntity<?> deleteEventById(@PathVariable Long id) {
    boolean deletionStatus = eventRepo.deleteById(id);

    if (deletionStatus == false) throw new ResourceNotFoundException("AEvent-Id does not exist.");

    return new ResponseEntity<>(deletionStatus, HttpStatus.OK);
  }

}
