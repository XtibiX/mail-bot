package com.fortytwotalents.events.repository.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fortytwotalents.events.RepositoryConfig;
import com.fortytwotalents.events.domain.Event;
import com.fortytwotalents.events.repository.EventRepository;

/**
 * Integration tests for the {@link JpaEventRepository} implementation.
 * 
 * @author Patrick Baumgartner
 * @since 1.0
 */
@ContextConfiguration(classes = RepositoryConfig.class)
public class JpaEventRepositoryTests extends AbstractTransactionalJUnit4SpringContextTests {

	private static final String EVENTS_TABLES = "event";

	@Autowired
	private EventRepository eventRepository;

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
		List<Event> events = eventRepository.findAll();
		assertNotNull(events);
		assertEquals(1, events.size());
	}

	@Test
	public void findById() {
		Event event = eventRepository.findById(1L);
		assertNotNull(event);
	}

	@Test
	public void saveWithMinimumRequiredFields() {
		assertNumEvents(1);

		Event event = new Event();
		event.setName("test event");

		Event savedEvent = eventRepository.save(event);
		flushSession();

		assertNotNull(savedEvent.getId());
		assertNumEvents(2);

		event.setId(2L);
		assertEquals(event, eventRepository.findById(savedEvent.getId()));
	}

	@Test
	public void update() {
		assertNumEvents(1);

		Event event = eventRepository.findById(1L);
		assertNotNull(event);
		event.setName("updated name");

		Event updatedEvent = eventRepository.save(event);
		flushSession();

		assertNumEvents(1);
		String updatedName = jdbcTemplate.queryForObject("select name from event where id=?", String.class,
				updatedEvent.getId());

		assertEquals("updated name", updatedName);
	}

	@Test
	public void delete() {
		Event event = eventRepository.findById(1L);
		assertNotNull(event);
		eventRepository.delete(event);
		flushSession();
		assertNumEvents(0);
	}

}
