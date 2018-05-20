package com.fortytwotalents.events.service.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fortytwotalents.events.EventsConfig;
import com.fortytwotalents.events.domain.Event;
import com.fortytwotalents.events.service.EventService;

/**
 * Integration tests for the {@link StandardEventService}.
 * 
 * @author Patrick Baumgartner
 * @since 1.0
 */
@ActiveProfiles("dev")
@ContextConfiguration(classes = EventsConfig.class)
public class StandardEventServiceTests extends AbstractTransactionalJUnit4SpringContextTests {

	private static final String EVENTS_TABLES = "event";

	@Autowired
	private EventService eventService;

	@PersistenceContext
	private EntityManager entityManager;

	private void flushSession() {
		entityManager.flush();
	}

	private void assertNumEvents(int expectedNumRows) {
		int numRowsInTable = countRowsInTable(EVENTS_TABLES);
		assertEquals("Number of rows in table [" + EVENTS_TABLES + "].", expectedNumRows, numRowsInTable);
	}

	@Test
	public void findAll() {
		List<Event> events = eventService.findAllEvents();

		assertNotNull(events);
		assertEquals(1, events.size());
	}

	@Test
	public void save() {
		assertNumEvents(1);

		Event event = new Event();
		event.setName("test event");

		Event savedEvent = eventService.save(event);

		assertNotNull(savedEvent.getId());
		assertNumEvents(2);
	}

	@Test
	public void delete() {
		Event event = eventService.findById(1L);
		assertNotNull(event);

		eventService.delete(event);
		flushSession();

		assertNumEvents(0);
	}

}
