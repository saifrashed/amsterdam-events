package com.aeserver.repository;

import com.aeserver.model.AEvent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AEventsRepositoryMock extends AbstractEntityRepositoryMock<AEvent> {

  public AEventsRepositoryMock() {
    super(AEvent.class);

    entities.add(AEvent.createRandomAEvent());
    entities.add(AEvent.createRandomAEvent());
    entities.add(AEvent.createRandomAEvent());
    entities.add(AEvent.createRandomAEvent());
    entities.add(AEvent.createRandomAEvent());
    entities.add(AEvent.createRandomAEvent());
    entities.add(AEvent.createRandomAEvent());
  }
}
