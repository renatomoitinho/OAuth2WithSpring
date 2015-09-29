package br.inf.audasi.api.service;

import br.inf.audasi.domain.details.ApiUserDetails;
import br.inf.audasi.domain.entity.User;
import br.inf.audasi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Service
public class SingleUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByLogin(login);
        if (optional.isPresent()) {
            return new ApiUserDetails(optional.get());
        }
        throw new UsernameNotFoundException(String.format("User %s does not exist!", login));
    }
}
