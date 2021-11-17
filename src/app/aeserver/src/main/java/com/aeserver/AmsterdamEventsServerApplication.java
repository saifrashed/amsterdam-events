package com.aeserver;

import com.aeserver.model.AEvent;
import com.aeserver.repository.AEventsRepositoryJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AmsterdamEventsServerApplication implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  AEventsRepositoryJpa repository;

  public static void main(String[] args) {
    SpringApplication.run(AmsterdamEventsServerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    AEvent event1 = AEvent.createRandomAEvent();
    AEvent event2 = AEvent.createRandomAEvent();
    AEvent event3 = AEvent.createRandomAEvent();
    AEvent event4 = AEvent.createRandomAEvent();

    repository.save(event1);
    repository.save(event2);
    repository.save(event3);
    repository.save(event4);

    logger.info("AEvent id "+event1.getId()+" -> {}", repository.findById(event1.getId()));

    AEvent newEvent = AEvent.createRandomAEvent();

    logger.info("Inserting -> {"+newEvent.getId()+"}",
      repository.save(newEvent));

  }

}
