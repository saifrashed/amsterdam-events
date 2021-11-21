package com.aeserver.repository;

import com.aeserver.model.AEvent;
import com.aeserver.model.Registration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * AEvent repository with JPA implementation
 */
@Primary
@Repository("AEVENT.JPA")
@Transactional
public class AEventsRepositoryJpa extends AbstractEntityRepositoryJpa<AEvent> {

  /**
   * Empty constructor.
   */
  public AEventsRepositoryJpa() {
    super(AEvent.class);
  }

  /**
   * Get value of total number registrations.
   *
   * @param id Event to be counted.
   * @return Size value of registration list.
   */
  public int getNumberOfRegistrations(long id) {
    AEvent aevent = findById(id);

    return aevent.getRegistrations().size();
  }

  /**
   * Add registration to event.
   *
   * @param id           Event to be added to.
   * @param registration Registration to add.
   * @return Updated event
   */
  public AEvent addRegistration(long id, Registration registration) {

    AEvent aevent = findById(id);

    aevent.addRegistration(registration);
    registration.setAEvent(aevent);

    entityManager.persist(registration);

    return aevent;
  }

  ;
}
