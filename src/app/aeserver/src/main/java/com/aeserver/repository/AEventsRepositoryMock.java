package com.aeserver.repository;

import com.aeserver.model.AEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AEventsRepositoryMock implements AEventsRepository{

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
  public List<AEvent> findAll() {
    return events;
  }
}
