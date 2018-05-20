package com.fortytwotalents.events.repository;

import java.util.List;

import com.fortytwotalents.events.domain.Event;

/**
 * @author Patrick Baumgartner
 * @since 1.0
 */
public interface EventRepository {

	Event findById(Long id);

	List<Event> findAll();

	Event save(Event event);

	void delete(Event event);

}
