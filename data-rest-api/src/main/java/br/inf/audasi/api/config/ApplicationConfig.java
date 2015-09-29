package br.inf.audasi.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
* @author renatomoitinhodias@gmail.com
*/
@Configuration
@ComponentScan(basePackages = "br.inf.audasi.api")
@Import(br.inf.audasi.domain.config.ApplicationConfig.class)
public class ApplicationConfig {
}
