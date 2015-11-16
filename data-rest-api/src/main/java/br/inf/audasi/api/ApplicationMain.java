package br.inf.audasi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author renatomoitinhodias@gmail.com
 */
@SpringBootApplication
@EnableAutoConfiguration
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
