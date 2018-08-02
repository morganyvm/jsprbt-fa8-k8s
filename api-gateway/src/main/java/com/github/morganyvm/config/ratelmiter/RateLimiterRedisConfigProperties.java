/**
 * 
 */
package com.github.morganyvm.config.ratelmiter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author morgany
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@ConfigurationProperties(prefix = "api.ratelimiter.redis", ignoreUnknownFields = true)
public class RateLimiterRedisConfigProperties {

	@NestedConfigurationProperty
	private ServerProperties standAlone;
	
	@NestedConfigurationProperty
	private ClusterProperties cluster;
}
