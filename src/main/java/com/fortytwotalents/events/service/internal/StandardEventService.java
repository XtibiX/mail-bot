package com.fortytwotalents.events.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.fortytwotalents.events.aspect.ProfileExecution;
import com.fortytwotalents.events.domain.Event;
import com.fortytwotalents.events.repository.EventRepository;
import com.fortytwotalents.events.service.EventService;

import lombok.extern.slf4j.Slf4j;

/**
 * Standard implementation of the {@link EventService} API.
 * 
 * @author Patrick Baumgartner
 * @since 1.0
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class StandardEventService implements EventService {

	private final EventRepository repository;

	@Autowired
	public StandardEventService(EventRepository eventRepository) {
		this.repository = eventRepository;
	}

	public Event findById(Long id) {
		return repository.findById(id);
	}

	@ProfileExecution
	public List<Event> findAllEvents() {
		return repository.findAll();
	}

	@Transactional(readOnly = false)
	public Event save(final Event event) {
		Assert.notNull(event, "event must not be null");
		if (log.isDebugEnabled()) {
			log.debug("Saving new event: {}", event);
		}

		return repository.save(event);
	}

	@Transactional(readOnly = false)
	public void delete(Event event) {
		repository.delete(event);
	}

}
