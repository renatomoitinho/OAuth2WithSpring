package br.inf.audasi.api.controller;

import br.inf.audasi.domain.entity.User;
import br.inf.audasi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renatomoitinhodias@gmail.com
 */
@RestController
public class SampleController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home() {
        return "hello!!!";
    }

    @RequestMapping("/guest") @Secured("ROLE_GUEST")
    public String guest() {
        return "hello!!!";
    }

    @RequestMapping("/hello") @Secured("ROLE_USER")
    public String home(@AuthenticationPrincipal User user) {
        return String.format("Hello, %s!", user.getName());
    }

    @RequestMapping("/users") @Secured("ROLE_ADMIN")
    public @ResponseBody Iterable<User> getUsers() {
          return userRepository.findAll();
    }
}
