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

  List<AEvent> findAll(){
    AEventsRepositoryMock.findAll();
  };

  AEvent findById(long id){

  }
    return AEventsRepositoryMock.findById(id)
  };

  @Transactional
  AEvent save(AEvent aEvent){
    if (AEventsRepositoryMock.isNew(aEvent)) {
      em.persist(aEvent);
      return aEvent;
    } else {
      return em.merge(aEvent);
    };

  boolean deleteById(long id){

  };

  @Override
  public List<AEvent> findAll() {
    return events;
  }
}
