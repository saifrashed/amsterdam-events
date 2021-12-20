package com.aeserver.repository;

import com.aeserver.model.ExternalEvent;
import com.aeserver.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Primary
@Repository("USER.JPA")
@Transactional
public class UserRepositoryJpa extends AbstractEntityRepositoryJpa<User> {

  public UserRepositoryJpa() {
    super(User.class);
  }

  public User addExternalEvent(long id, ExternalEvent event) {
    User user = findById(id);

    user.addEvents(event);
    event.setUser(user);

    entityManager.merge(event);
    return user;
  }

}
