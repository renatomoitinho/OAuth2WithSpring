package br.inf.audasi.api.service;

import br.inf.audasi.domain.entity.User;
import br.inf.audasi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Repository
@Cacheable(value="userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByLogin(String login) {
        Optional<User> optional = userRepository.findByLogin(login);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new UsernameNotFoundException(String.format("User %s does not exist!", login));
    }

}
