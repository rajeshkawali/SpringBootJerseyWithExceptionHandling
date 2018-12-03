package com.transport.controller;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.transport.exceptions.ServiceException;
import com.transport.model.Bus;
import com.transport.services.BusService;

@Component
@Path("/bus")
public class BusController {

	private static final Logger logger = LoggerFactory.getLogger(BusController.class);

	@Autowired
	private BusService busService;

	@GET
	@Path("/allbus")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBus() {
		logger.info("BusController.getAllBus()");
		// http://localhost:8091/transport/bus/allbus
		List<Bus> list = busService.getAllBus();
		return Response.ok(list).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBusById(@PathParam("id") Integer id) {
		logger.info("BusController.getBusById()");
		// http://localhost:8091/transport/bus/101
		Bus bus = busService.getBusById(id);
		return Response.ok(bus).build();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBus(Bus bus) {
		logger.info("BusController.addBus()");
		boolean isAdded = busService.addBus(bus);
		if (!isAdded) {
			logger.error("Bus already exits.");
			return Response.status(Status.CONFLICT).build();
		}
		return Response.created(URI.create("/transport/bus/" + bus.getId())).build();
	}

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBus(Bus bus) {
		logger.info("BusController.updateBus()");
		busService.updateBus(bus);
		return Response.ok(bus).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBus(@PathParam("id") Integer id) {
		logger.info("BusController.deleteBus()");
		busService.deleteBus(id);
		return Response.noContent().build();
	}

	@GET
	@Path("/error")
	public Response sampleError() throws ServiceException {
		// http://localhost:8091/transport/bus/error
		logger.error("Sample Error Message");
		throw new ServiceException(HttpStatus.NOT_FOUND.value(), "Sample Error Message", 1);
	}

	@GET
	@Path("/error/generic")
	public Response sampleGenericError() {
		// http://localhost:8091/transport/bus/error/generic
		logger.error("Null Pointer Exception");
		throw new NullPointerException();
	}

}
