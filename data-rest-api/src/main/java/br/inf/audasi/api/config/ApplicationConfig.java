package br.inf.audasi.api.config;

import br.inf.audasi.api.helper.ApiAuthorization;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Configuration
@EnableCaching
@ComponentScan(basePackages = "br.inf.audasi.api")
@Import(br.inf.audasi.domain.config.ApplicationConfig.class)
public class ApplicationConfig {

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //JsonRedisSerializer<Optional> optionalJsonDeserializer = new JsonRedisSerializer<>(Optional.class);
        template.setConnectionFactory(factory);
        //template.setValueSerializer(optionalJsonDeserializer);
        return template;
    }

    @Bean
    @Autowired
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JSR310Module())
                .registerModule(new Jdk8Module());
    }

    @Bean
    public ApiAuthorization apiAuthorization() throws IOException {
        byte[] bytes = IOUtils.toByteArray(getClass().getResourceAsStream("/AuthorizationServerConfiguration.json"));
        return objectMapper().readValue(bytes, ApiAuthorization.class);
    }

}
