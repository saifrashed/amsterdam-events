package com.aeserver.controller;

import com.aeserver.exception.PreConditionalFailedException;
import com.aeserver.exception.ResourceNotFoundException;
import com.aeserver.model.AEvent;
import com.aeserver.model.Registration;
import com.aeserver.repository.AEventsRepositoryJpa;
import com.aeserver.repository.AEventsRepositoryMock;
import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AEvent Controller
 */
@CrossOrigin(origins = "*") //this line
@RestController
@RequestMapping("/api")
public class AEventsController {

  /**
   * JPA repository
   */
  @Autowired
  AEventsRepositoryJpa eventRepo;

  /**
   * @return List of events.
   */
  @GetMapping("/aevent")
  public ResponseEntity<List<AEvent>> getEvents() {
    List<AEvent> events = eventRepo.findAll();
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  /**
   * @return List of summary view.
   */
  @GetMapping("/aevent/summary")
  @JsonView(Views.Summary.class)
  public ResponseEntity<List<AEvent>> getEventSummary() {


    List<AEvent> events = eventRepo.findAll();
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  /**
   * @param id Event id to retrieve.
   * @return Httpstatus & found event object.
   */
  @GetMapping("/aevent/{id}")
  public ResponseEntity<AEvent> getEventById(@PathVariable Long id) {
    AEvent events = eventRepo.findById(id);
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  /**
   * @param event Event response body.
   * @return Httpstatus & added event object.
   */
  @PostMapping("/aevent")
  public ResponseEntity<AEvent> saveEvents(@RequestBody AEvent event) {
    AEvent events = eventRepo.save(event);
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  /**
   * @param registration Added registration request body
   * @param aeventId     Event id to be updated.
   * @return Httpstatus & updated event object.
   */
  @PostMapping("/aevents/{aeventId}/register")
  public ResponseEntity<AEvent> addEventRegistration(@RequestBody Registration registration, @PathVariable long aeventId) {

    AEvent aevent = eventRepo.findById(aeventId);

    if (aevent.getStatus() != AEvent.AEventStatus.PUBLISHED)
      throw new PreConditionalFailedException("AEvent with AEvent-Id=" + aevent.getId() + " is not published");

    if (eventRepo.getNumberOfRegistrations(aeventId) >= aevent.getMaxParticipants())
      throw new PreConditionalFailedException("AEvent with AEvent-Id=" + aevent.getId() + " is not published");

    AEvent events = eventRepo.addRegistration(aeventId, registration);

    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  /**
   * @param id    Event id to be updated.
   * @param event Updated event object.
   * @return Httpstatus & updated event object.
   */
  @PutMapping("/aevent/{id}")
  @ResponseBody
  public ResponseEntity<AEvent> putEventById(@PathVariable Long id, @RequestBody AEvent event) {

    if (event.getId() != id)
      throw new PreConditionalFailedException("AEvent-Id=" + event.getId() + " does not match path parameter=" + id);

    AEvent events = eventRepo.save(event);
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  /**
   * Delete AEvent.
   *
   * @param id Event
   * @return boolean request status
   */
  @DeleteMapping("/aevent/{id}")
  public ResponseEntity<?> deleteEventById(@PathVariable Long id) {
    boolean deletionStatus = eventRepo.deleteById(id);

    if (deletionStatus == false) throw new ResourceNotFoundException("AEvent-Id does not exist.");

    return new ResponseEntity<>(deletionStatus, HttpStatus.OK);
  }

}
