package br.inf.audasi.api.controller;

import br.inf.audasi.api.service.UserService;
import br.inf.audasi.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renatomoitinhodias@gmail.com
 */
@RestController
@RequestMapping(value = "/api", produces = {"application/json;charset=UTF-8"})
public class SampleController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home() {
        return "hello!!!";
    }

    @Secured("ROLE_GUEST")
    @RequestMapping("/guest")
    public String guest() {
        return "hello!!!";
    }

    @Secured("ROLE_GUEST")
    @RequestMapping("/hello")
    public String home(@AuthenticationPrincipal User user) {
        return String.format("Hello, %s!", user.getName());
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/users")
    public Iterable<User> getUsers() {
        return userService.findAll();
    }
}
