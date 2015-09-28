package br.inf.audasi.api.controller;

import br.inf.audasi.domain.entity.User;
import br.inf.audasi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "Hello World!";
    }

    @RequestMapping("/users")
    public @ResponseBody Iterable<User> getUsers(){
          return userRepository.findAll();
    }
}
