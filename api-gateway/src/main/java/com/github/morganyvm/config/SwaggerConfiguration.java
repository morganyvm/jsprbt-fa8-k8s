/**
 * 
 */
package com.github.morganyvm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author morgany
 *
 */
@Configuration
public class SwaggerConfiguration {

	@Autowired
	private GatewayProperties properties;
	
//	@Primary
//	@Bean
//	public SwaggerResourcesProvider swaggerResourcesProvider() {
//
//		return () -> properties.getRoutes().stream()
//					.map(route -> createResource(route.getId(), "2.0"))
//					.collect(Collectors.toList());
//	}
//
//	private SwaggerResource createResource(String location, String version) {
//		
//		SwaggerResource swaggerResource = new SwaggerResource();
//		
//		swaggerResource.setName(location);
//		swaggerResource.setLocation("/" + location + "/v2/api-docs");
//		swaggerResource.setSwaggerVersion(version);
//		
//		return swaggerResource;
//	}
}
