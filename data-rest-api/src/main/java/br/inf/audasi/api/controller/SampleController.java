package br.inf.audasi.api.controller;

import br.inf.audasi.api.service.UserService;
import br.inf.audasi.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author renatomoitinhodias@gmail.com
 */
@RestController
@RequestMapping(value = "/api", produces = {"application/json;charset=UTF-8"})
public class SampleController extends ApiController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = GET)
    public String guest() {
        return "hello!!!";
    }

    @RequestMapping(value = "/test", method = GET)
    public String test() {
        return "test 123!!!";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/user", method = GET)
    public String home(@AuthenticationPrincipal User user) {
        return String.format("Hello, %s!", user.getName());
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/users", method = GET)
    public @ResponseBody Iterable<User> getUsers() {
        return userService.findAll();
    }
}
