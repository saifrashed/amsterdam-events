package com.aeserver.repository;

import com.aeserver.model.Registration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Primary
@Repository("REGISTRATION.JPA")
@Transactional
public class RegistrationRepositoryJpa extends AbstractEntityRepositoryJpa<Registration> {

  public RegistrationRepositoryJpa() {
    super(Registration.class);
  }

}
