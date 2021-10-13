package com.aeserver.repository;


import com.aeserver.model.AEvent;

import java.util.List;

public interface AEventsRepository  {
  public List<AEvent> findAll();
}
