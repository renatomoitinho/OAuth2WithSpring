package br.inf.audasi.api.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
* @author renatomoitinhodias@gmail.com
*/
@Configuration
@ComponentScan(basePackages = "br.inf.audasi.api")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@Import(br.inf.audasi.domain.config.ApplicationConfig.class)
public class ApplicationConfig {
}
