/**
 * 
 */
package com.github.morganyvm.config.ratelmiter;

import java.util.List;

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
public class ClusterProperties {

	@NestedConfigurationProperty
	private ServerProperties master;

	@NestedConfigurationProperty
	private List<ServerProperties> slaves;
}
