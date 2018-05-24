package no.normann.mail.service;


import no.normann.mail.domain.User;

import java.util.List;

/**
 * @author Patrick Baumgartner
 * @since 1.0
 */
public interface UserService {

	User findById(Long id);

	List<User> findAllUsers();

	User save(User user);

	void delete(User user);

}
