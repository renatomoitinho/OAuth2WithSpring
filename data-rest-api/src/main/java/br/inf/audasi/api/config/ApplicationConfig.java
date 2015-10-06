package br.inf.audasi.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Configuration
@EnableCaching
@ComponentScan(basePackages = "br.inf.audasi.api")
@PropertySource("classpath:redis.properties")
@Import(br.inf.audasi.domain.config.ApplicationConfig.class)
public class ApplicationConfig {

    @Autowired
    private Environment env;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(env.getProperty("redis.host"));
        factory.setPort(env.getProperty("redis.port", Integer.class));
        factory.setUsePool(env.getProperty("redis.use.pool", Boolean.class));
        factory.setTimeout(env.getProperty("redis.timeout", Integer.class));
        return factory;
    }

    @Bean
    public RedisOperations redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager() {
        CacheManager cacheManager = new RedisCacheManager(redisTemplate());
        return cacheManager;
    }
}
