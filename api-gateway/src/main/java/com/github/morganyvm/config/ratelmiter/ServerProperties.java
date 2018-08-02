/**
 * 
 */
package com.github.morganyvm.config.ratelmiter;

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
public class ServerProperties {

	private String hostName;
	
	private int port;
	
}
