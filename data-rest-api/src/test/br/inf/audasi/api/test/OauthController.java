package br.inf.audasi.api.test;

import br.inf.audasi.api.config.ApplicationConfig;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

/**
 * @author renatomoitinhodias@gmail.com
 *
 * https://github.com/jayway/rest-assured/wiki/GettingStarted
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class OauthController {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void canAuthentication() throws IOException {
        when().get("/api/users")
                .then()
                .body("error", is("unauthorized"));
    }

}
