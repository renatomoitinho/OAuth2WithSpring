package br.inf.audasi.api.controller;

import br.inf.audasi.domain.entity.User;
import br.inf.audasi.domain.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renatomoitinhodias@gmail.com
 */
@RestController
@RequestMapping(value = "/api",  method = RequestMethod.GET , produces = {"application/json;charset=UTF-8"})
public class SampleController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/guest", method = RequestMethod.GET)
    public String guest() {
        return "hello!!!";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @JsonIgnore
    public String home(@AuthenticationPrincipal User user) {
        return String.format("Hello, %s!", user.getName());
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/users", method = RequestMethod.GET)

    @ApiOperation(
            value = "find all users",
            response = User.class ,
            responseContainer = "List",
            authorizations = {
                    @Authorization(value="sampleOauth", scopes = {
                            @AuthorizationScope(scope = "read:user", description = "read scopes")}
                    )}
    )
    @ApiResponses({
            @ApiResponse(code =  401, message ="{\"error\":\"unauthorized\",\"error_description\":\"Full authentication is required to access this resource\"}")
    })
    public @ResponseBody Iterable<User> getUsers() {
          return userRepository.findAll();
    }
}
