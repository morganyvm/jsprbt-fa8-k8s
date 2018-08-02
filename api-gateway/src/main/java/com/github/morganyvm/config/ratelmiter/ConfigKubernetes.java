/**
 * 
 */
package com.github.morganyvm.config.ratelmiter;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author morgany
 *
 */
@Profile("default")
public class ConfigKubernetes {

	@Bean
	public ReactiveRedisConnectionFactory lettuceConnectionFactory(
			@Value("${ratelimiter-redis.stand-alone}") boolean standalone,
			@Value("${ratelimiter-redis.stand-alone.host-name}") String host,
			@Value("${ratelimiter-redis.stand-alone.port}") int port,
			@Value("${ratelimiter-redis.master.host-name}") String masterHost,
			@Value("${ratelimiter-redis.master.port}") int masterPort,
			@Value("${ratelimiter-redis.slaves}") Map<String, Integer> slaves) {

		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().useSsl().and()
				.commandTimeout(Duration.ofSeconds(2)).shutdownTimeout(Duration.ZERO).build();

		return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port), clientConfig);
	}
}
