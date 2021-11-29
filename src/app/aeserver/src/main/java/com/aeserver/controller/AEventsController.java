package com.aeserver.controller;

import com.aeserver.exception.PreConditionalFailedException;
import com.aeserver.exception.ResourceNotFoundException;
import com.aeserver.model.AEvent;
import com.aeserver.model.Registration;
import com.aeserver.repository.AEventsRepositoryJpa;
import com.aeserver.repository.AEventsRepositoryMock;
import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.*;
import java.util.stream.Collectors;

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

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * @return List of events.
   */
  @GetMapping("/aevent")
  @JsonView(value = Views.Summary.class)
  public ResponseEntity<List<AEvent>> getEvents(@RequestParam Optional<String> title, @RequestParam Optional<String> status, @RequestParam Optional<Integer> minRegistrations) {

    List<String> statusList = EnumSet.allOf(AEvent.AEventStatus.class).stream().map(e -> e.name()).collect(Collectors.toList()); // list of enum as string
    List<AEvent> events = eventRepo.findAll(); // list for events

    int amountParams = 0;

    if (title.isPresent()) {
      events = eventRepo.findByQuery("AEvent_find_by_title", title.get());
      amountParams++;
    }

    if (status.isPresent()) {
      // status check
      if (status.isPresent() && !statusList.contains(status.get()))
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status=" + status.get() + " is not a valid AEvent status value");

      events = eventRepo.findByQuery("AEvent_find_by_status", AEvent.AEventStatus.valueOf(status.get()));
      amountParams++;
    }

    if (minRegistrations.isPresent()) {
      events = eventRepo.findByQuery("AEvent_find_by_minRegistrations", minRegistrations.get());
      amountParams++;
    }

    // check amount params
    if (amountParams > 1)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can only handle one request paramater title=, status= or minRegistration=");

    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  /**
   * @return List of summary view.
   */
  @GetMapping("/aevent/summary")
  @JsonView({Views.Summary.class})
  public ResponseEntity<List<AEvent>> getEventSummary() {


    List<AEvent> events = eventRepo.findAll();
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  /**
   * @param id Event id to retrieve.
   * @return Httpstatus & found event object.
   */
  @GetMapping("/aevent/{id}")
  @JsonView({Views.Unrestricted.class})
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
  @PostMapping("/aevent/{aeventId}/register")
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
   * Get registrations for given event
   * @param aeventId
   * @return
   */
  @GetMapping("/aevent/{aeventId}/registrations")
  @JsonView({Views.Summary.class})
  public ResponseEntity<List<Registration>> getEventRegistrations(@PathVariable long aeventId) {

    List<Registration> registrations = eventRepo.getRegistrations(aeventId);

    return new ResponseEntity<>(registrations, HttpStatus.OK);
  }

  /**
   * Get registration for given event
   * @param aeventId
   * @return
   */
  @GetMapping("/aevent/{aeventId}/registrations/{registrationId}")
  @JsonView({Views.Unrestricted.class})
  public ResponseEntity<Registration> getEventRegistration(@PathVariable long aeventId, @PathVariable long registrationId) {

    Registration registration = eventRepo.getRegistration(aeventId, registrationId);

    return new ResponseEntity<>(registration, HttpStatus.OK);
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
