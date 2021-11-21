package com.aeserver.repository;

import com.aeserver.repository.interfaces.EntityRepository;
import com.aeserver.repository.interfaces.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * AEvent repository with JPA implementation
 */
@Transactional
public abstract class AbstractEntityRepositoryJpa<E extends Identifiable> implements EntityRepository<E> {

  /**
   * EntityManager containing context
   */
  @PersistenceContext
  EntityManager entityManager;

  private Class<E> theEntityClass;

  public AbstractEntityRepositoryJpa(Class<E> entityClass) {
    this.theEntityClass = entityClass;
    System.out.println("Created: " + this.getClass().getName() + "<" + this.theEntityClass.getSimpleName() + ">");
  }

  @Override
  public E save(E entity) {
    return entityManager.merge(entity);
  }

  @Override
  public E findById(long id) {
    return entityManager.find(this.theEntityClass, id);
  }

  @Override
  public boolean deleteById(long id) {
    if (id != 0) {
      E entity = findById(id);
      entityManager.remove(entity);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public List<E> findAll() {
    CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<E> cq = builder.createQuery(this.theEntityClass);
    Root<E> root = cq.from(this.theEntityClass);
    cq.select(root);
    return this.entityManager.createQuery(cq).getResultList();
  }
}
