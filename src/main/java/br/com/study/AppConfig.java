package br.com.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class AppConfig {
	
	@Value("${spring.redis.port}")
	private int redisPort;
	@Value("${spring.redis.host}")
	private String redisHost;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(
      RedisProperties redisProperties) {
        return new LettuceConnectionFactory(
          redisProperties.getHost(), 
          redisProperties.getPort());
    }
 
    @Bean
    public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
    
    @Bean
    public RedisServer redisServer(RedisProperties redisProperties) {
    	return new RedisServer(redisProperties.getHost(), redisProperties.getPort());
    	
    }
    
    
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//    	return new JedisConnectionFactory();
//    }
//    
//    @Bean
//    public RedisTemplate<String, Student> redisTemplate() {
//        RedisTemplate<String, Student> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }
	
	
}
