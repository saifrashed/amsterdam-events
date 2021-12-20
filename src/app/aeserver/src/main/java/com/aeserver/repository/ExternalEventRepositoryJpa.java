package com.aeserver.repository;

import com.aeserver.model.AEvent;
import com.aeserver.model.ExternalEvent;
import com.aeserver.model.Registration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * AEvent repository with JPA implementation
 */
@Primary
@Repository("EXTERNALEVENT.JPA")
@Transactional
public class ExternalEventRepositoryJpa extends AbstractEntityRepositoryJpa<ExternalEvent> {

  /**
   * Empty constructor.
   */
  public ExternalEventRepositoryJpa() {
    super(ExternalEvent.class);
  }
}
