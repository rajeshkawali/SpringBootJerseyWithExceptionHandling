package com.transport.configuration;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.transport.controller.BusController;
import com.transport.exceptions.GenericExceptionMapper;
import com.transport.exceptions.ServiceExceptionMapper;

@Component
@ApplicationPath("/transport")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		
		// Registering Controller
		register(BusController.class);
		// Registering the Exception Mappers
		register(ServiceExceptionMapper.class);
        register(GenericExceptionMapper.class);
	}
}
