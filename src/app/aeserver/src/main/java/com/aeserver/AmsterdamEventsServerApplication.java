package com.aeserver;

import com.aeserver.model.AEvent;
import com.aeserver.model.Registration;
import com.aeserver.model.User;
import com.aeserver.repository.AEventsRepositoryJpa;
import com.aeserver.repository.RegistrationRepositoryJpa;
import com.aeserver.repository.UserRepositoryJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmsterdamEventsServerApplication implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  AEventsRepositoryJpa aeventRepo;

  @Autowired
  UserRepositoryJpa userRepo;

  @Autowired
  RegistrationRepositoryJpa registrationRepo;

  public static void main(String[] args) {
    SpringApplication.run(AmsterdamEventsServerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    AEvent event1 = AEvent.createRandomAEvent();
    AEvent event2 = AEvent.createRandomAEvent();
    AEvent event3 = AEvent.createRandomAEvent();
    AEvent event4 = AEvent.createRandomAEvent();

    aeventRepo.save(event1);
    aeventRepo.save(event2);
    aeventRepo.save(event3);
    aeventRepo.save(event4);

    logger.info("AEvent id " + event1.getId() + " -> {}", aeventRepo.findById(event1.getId()));

    AEvent newEvent = AEvent.createRandomAEvent();

    logger.info("Inserting -> {" + newEvent.getId() + "}",
      aeventRepo.save(newEvent));

    Registration registration1 = Registration.createRandomRegistration(aeventRepo.findById(20001));
    Registration registration2 = Registration.createRandomRegistration(aeventRepo.findById(20001));
    Registration registration3 = Registration.createRandomRegistration(aeventRepo.findById(20002));
    Registration registration4 = Registration.createRandomRegistration(aeventRepo.findById(20002));
    Registration registration5 = Registration.createRandomRegistration(aeventRepo.findById(20004));
    Registration registration6 = Registration.createRandomRegistration(aeventRepo.findById(20005));

    registrationRepo.save(registration1);
    registrationRepo.save(registration2);
    registrationRepo.save(registration3);
    registrationRepo.save(registration4);
    registrationRepo.save(registration5);
    registrationRepo.save(registration6);


    User user1 = new User("userOne", "userOne@icloud.com", "userOne", true);
    User user2 = new User("userTwo", "userTwo@icloud.com", "userTwo", false);
    User user3 = new User("userThree", "userThree@icloud.com", "userThree", false);
    User user4 = new User("userFour", "userFour@icloud.com", "userFour", true);

    userRepo.save(user1);
    userRepo.save(user2);
    userRepo.save(user3);
    userRepo.save(user4);
  }

}
