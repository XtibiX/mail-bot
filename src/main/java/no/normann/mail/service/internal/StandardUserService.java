package no.normann.mail.service.internal;

import com.fortytwotalents.users.aspect.ProfileExecution;
import lombok.extern.slf4j.Slf4j;
import no.normann.mail.domain.User;
import no.normann.mail.repository.UserRepository;
import no.normann.mail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
public class StandardUserService implements UserService {

    private final UserRepository repository;

    @Autowired
    public StandardUserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public User findById(Long id) {
        return repository.findById(id);
    }

    @ProfileExecution
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Transactional
    public User save(final User user) {
        Assert.notNull(user, "user must not be null");
        log.debug("Saving new user: {}", user);

        return repository.save(user);
    }

    @Transactional
    public void delete(User user) {
        repository.delete(user);
    }

}
