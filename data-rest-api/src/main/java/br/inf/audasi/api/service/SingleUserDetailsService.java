package br.inf.audasi.api.service;

import br.inf.audasi.domain.details.ApiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Service
public class SingleUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new ApiUserDetails(userService.findByLogin(login));
    }
}
