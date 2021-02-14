package org.jp.micros.voter.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConfigurationProperties(prefix = "voter.app.redis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisConfig {

	private String hostName;
	private int port;

	@Bean
	public RedisStandaloneConfiguration RedisStandaloneConfiguration() {
		return new RedisStandaloneConfiguration(hostName, port);
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory(
			final RedisStandaloneConfiguration redisStandaloneConfiguration) {
		return new LettuceConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(final RedisConnectionFactory redisConnectionFactory) {
		final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(RedisSerializer.string());
		redisTemplate.setValueSerializer(RedisSerializer.string());
		return redisTemplate;
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
	}

}
