package com.fortytwotalents.events.service;

import java.util.List;

import com.fortytwotalents.events.domain.Event;

/**
 * @author Patrick Baumgartner
 * @since 1.0
 */
public interface EventService {

	Event findById(Long id);

	List<Event> findAllEvents();

	Event save(Event event);

	void delete(Event event);

}
