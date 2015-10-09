package br.inf.audasi.api.controller;

import br.inf.audasi.api.roles.RoleAdmin;
import br.inf.audasi.api.roles.RoleGuest;
import br.inf.audasi.api.roles.RoleUsers;
import br.inf.audasi.api.service.UserService;
import br.inf.audasi.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RoleGuest
    @RequestMapping("/guest")
    public String guest() {
        return "hello!!!";
    }

    @RoleUsers
    @RequestMapping("/hello")
    public String home(@AuthenticationPrincipal User user) {
        return String.format("Hello, %s!", user.getName());
    }

    @RoleAdmin
    @RequestMapping("/users")
    public @ResponseBody Iterable<User> getUsers() {
        return userService.findAll();
    }
}
