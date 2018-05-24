package com.fortytwotalents.users.repository.internal;

import no.normann.mail.domain.User;
import no.normann.mail.jpa.RepositoryConfig;
import no.normann.mail.repository.UserRepository;
import no.normann.mail.repository.internal.JpaUserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration tests for the {@link JpaUserRepository} implementation.
 *
 * @author Patrick Baumgartner
 * @since 1.0
 */
@ContextConfiguration(classes = RepositoryConfig.class)
public class JpaUserRepositoryTests extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String EVENTS_TABLES = "user";

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private void flushSession() {
        entityManager.flush();
    }

    private void assertNumUsers(int expectedNumRows) {
        int numRowsInTable = countRowsInTable(EVENTS_TABLES);
        assertEquals("Number of rows in table [" + EVENTS_TABLES + "].", expectedNumRows, numRowsInTable);
    }

    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void findById() {
        User user = userRepository.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void saveWithMinimumRequiredFields() {
        assertNumUsers(1);

        User user = new User();
        user.setName("test user");

        User savedUser = userRepository.save(user);
        flushSession();

        assertNotNull(savedUser.getId());
        assertNumUsers(2);

        user.setId(2L);
        assertEquals(user, userRepository.findById(savedUser.getId()));
    }

    @Test
    public void update() {
        assertNumUsers(1);

        User user = userRepository.findById(1L);
        assertNotNull(user);
        user.setName("updated name");

        User updatedUser = userRepository.save(user);
        flushSession();

        assertNumUsers(1);
        String updatedName = jdbcTemplate.queryForObject("select name from user where id=?", String.class,
                updatedUser.getId());

        assertEquals("updated name", updatedName);
    }

    @Test
    public void delete() {
        User user = userRepository.findById(1L);
        assertNotNull(user);
        userRepository.delete(user);
        flushSession();
        assertNumUsers(0);
    }

}
