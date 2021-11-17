package com.aeserver.repository;

import com.aeserver.model.AEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AEventsRepositoryMock implements AEventsRepository {

  List<AEvent> events = new ArrayList<>();

  public AEventsRepositoryMock() {
    events.add(AEvent.createRandomAEvent());
    events.add(AEvent.createRandomAEvent());
    events.add(AEvent.createRandomAEvent());
    events.add(AEvent.createRandomAEvent());
    events.add(AEvent.createRandomAEvent());
    events.add(AEvent.createRandomAEvent());
    events.add(AEvent.createRandomAEvent());
  }

  @Override
  public AEvent save(AEvent aEvent) {
    if (aEvent.getId() == 0) {
      aEvent.setId(AEvent.idCounter++);
      events.add(aEvent);
    } else {
      events.add(aEvent);
    }

    return aEvent;
  }

  @Override
  public AEvent findById(long id) {

    for (AEvent event : events) {
      if ((long) event.getId() == id) {
        return event;
      }
    }

    return null;
  }

  @Override
  public boolean deleteById(long id) {

    for (AEvent event : events) {
      if ((long) event.getId() == id) {
        events.remove(event);
        return true;
      }
    }

    return false;
  }

  @Override
  public List<AEvent> findAll() {
    return events;
  }
}
