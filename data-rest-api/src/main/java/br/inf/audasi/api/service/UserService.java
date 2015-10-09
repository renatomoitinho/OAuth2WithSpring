package br.inf.audasi.api.service;

import br.inf.audasi.domain.entity.User;
import br.inf.audasi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByLogin(String login) {
        Optional<User> optional = userRepository.findByLogin(login);
        return optional.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s does not exist!", login)));
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

}
