package com.aeserver.repository;

import com.aeserver.model.AEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
@Transactional
public class AEventsRepositoryJpa implements AEventsRepository {

  // connect to the database
  @PersistenceContext
  EntityManager entityManager;

  public AEventsRepositoryJpa() {

  }

  @Override
  public AEvent save(AEvent aEvent) {
    return entityManager.merge(aEvent);
  }

  @Override
  public AEvent findById(long id) {
    return entityManager.find(AEvent.class, id);
  }

  @Override
  public boolean deleteById(long id) {
    if (id != 0) {
      AEvent aevent = findById(id);
      entityManager.remove(aevent);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public List<AEvent> findAll() {
    TypedQuery<AEvent> namedQuery = entityManager.createNamedQuery("find_all_events", AEvent.class);
    return namedQuery.getResultList();
  }
}
