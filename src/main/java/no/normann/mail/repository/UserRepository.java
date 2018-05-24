package no.normann.mail.repository;

import com.fortytwotalents.users.domain.Event;
import no.normann.mail.domain.User;

import java.util.List;

/**
 * @author Patrick Baumgartner
 * @since 1.0
 */
public interface UserRepository {

	User findById(Long id);

	List<User> findAll();

	User save(User user);

	void delete(User user);

}
