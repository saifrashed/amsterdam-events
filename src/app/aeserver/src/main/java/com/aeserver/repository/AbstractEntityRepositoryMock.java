package com.aeserver.repository;

import com.aeserver.model.AEvent;
import com.aeserver.repository.interfaces.EntityRepository;
import com.aeserver.repository.interfaces.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * AEvent repository with JPA implementation
 */
@Transactional
public abstract class AbstractEntityRepositoryMock<E extends Identifiable> implements EntityRepository<E> {

  /**
   * EntityManager containing context
   */
  @PersistenceContext
  EntityManager entityManager;

  private Class<E> theEntityClass;

  List<E> entities = new ArrayList<>();

  public AbstractEntityRepositoryMock(Class<E> entityClass) {
    this.theEntityClass = entityClass;
    System.out.println("Created: " + this.getClass().getName() + "<" + this.theEntityClass.getSimpleName() + ">");
  }

  @Override
  public E save(E entity) {
    if (entity.getId() == 0) {
      entity.setId(AEvent.idCounter++);
      entities.add(entity);
    } else {
      entities.add(entity);
    }

    return entity;
  }

  @Override
  public E findById(long id) {

    for (E entity : entities) {
      if ((long) entity.getId() == id) {
        return entity;
      }
    }

    return null;
  }

  @Override
  public boolean deleteById(long id) {

    for (E entity : entities) {
      if ((long) entity.getId() == id) {
        entities.remove(entity);
        return true;
      }
    }

    return false;
  }

  @Override
  public List<E> findAll() {
    return entities;
  }
}
