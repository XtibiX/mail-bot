package no.normann.mail.repository.internal;

import no.normann.mail.domain.User;
import no.normann.mail.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Hibernate-based implementation of the {@link UserRepository} API.
 *
 * @author Patrick Baumgartner
 * @since 1.0
 */
@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User findById(Long id) {
        Assert.notNull(id, "id must not be null");
        return entityManager.find(User.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return entityManager.merge(user);
    }

    public void delete(User user) {
        Assert.notNull(user, "user must not be null");
        entityManager.remove(user);
    }

}
