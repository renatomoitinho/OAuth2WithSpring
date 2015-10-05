package br.inf.audasi.api.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Configuration
public class DocApiConfig {
    @Bean
    public Docket sample() {

        List<AuthorizationScope> scopes = new ArrayList(){{
                add(new AuthorizationScope("read:user", "read scopes"));
                add(new AuthorizationScope("write:user", "write scopes"));
            }};

        List<GrantType> grantTypes = new ArrayList(){{
            add(new ImplicitGrant(new LoginEndpoint("http://localhost:9000/oauth/token"),"access_token"));
        }};

        List<SecurityScheme> securitySchemes = new ArrayList(){{
            add(new OAuthBuilder().name("sampleOauth").scopes(scopes).grantTypes(grantTypes).build());
        }};

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("sample-api")
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes)
                .select()
                .paths(samplePaths())
                .build();
    }

    private Predicate<String> samplePaths() {
        return or(regex("/api.*"));
    }

    private Predicate<String> userOnlyEndpoints() {
        return input -> input.contains("user");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox petstore API")
                .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
                        + "took a " +
                        "galley of type and scrambled it to make a type specimen book. It has survived not only five " +
                        "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                        "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
                        "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                        "versions of Lorem Ipsum.")
                .termsOfServiceUrl("http://springfox.io")
                .contact("springfox")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
}
