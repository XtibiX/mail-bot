package com.fortytwotalents.events.repository.internal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.fortytwotalents.events.domain.Event;
import com.fortytwotalents.events.repository.EventRepository;

/**
 * Hibernate-based implementation of the {@link EventRepository} API.
 * 
 * @author Patrick Baumgartner
 * @since 1.0
 */
@Repository
public class JpaEventRepository implements EventRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Event findById(Long id) {
		Assert.notNull(id, "id must not be null");
		return  entityManager.find(Event.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Event> findAll() {
		return entityManager.createQuery("from Event").getResultList();
	}

	public Event save(Event event) {
		Assert.notNull(event, "event must not be null");
		return  entityManager.merge(event);
	}

	public void delete(Event event) {
		Assert.notNull(event, "event must not be null");
		entityManager.remove(event);
	}

}
