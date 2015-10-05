package br.inf.audasi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author renatomoitinhodias@gmail.com
 */
@SpringBootApplication
@EnableSwagger2
public class ApplicationMain {

    public class WebInitializer extends SpringBootServletInitializer {
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(ApplicationMain.class);
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ApplicationMain.class);
        app.run(args);
    }

}
