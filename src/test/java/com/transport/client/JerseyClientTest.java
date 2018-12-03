package com.transport.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.transport.model.Bus;





public class JerseyClientTest {

	public static void getAllBus() {

		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8091/transport/bus");
		WebTarget allBus = base.path("allbus");
		List<Bus> list = allBus.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Bus>>() {
		});
		list.stream().forEach(bus -> System.out.println(bus.getId() + ", " + bus.getFrom() + ", " + bus.getTo()));
		client.close();
	}

	public static void getBusById(Integer id) {

		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8091/transport/bus");
		// WebTarget sPath = base.path("allbus");
		WebTarget busById = base.path("{id}").resolveTemplate("id", id);
		Bus bus = busById.request(MediaType.APPLICATION_JSON).get(Bus.class);
		System.out.println(bus.getId() + ", " + bus.getFrom() + ", " + bus.getTo());
		client.close();
	}

	public static void deleteBus(Integer id) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8091/transport/bus");
		WebTarget busById = base.path("{id}").resolveTemplate("id", id);
		Response response = busById.request(MediaType.APPLICATION_JSON).delete();
		System.out.println("Response Http Status: " + response.getStatus());
		if (response.getStatus() == 204) {
			System.out.println("Data deleted successfully.");
		}
		client.close();
	}
	
	public static void updateBus(Bus bus) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8091/transport/bus");
		WebTarget update = base.path("update");
		Response response = update.request(MediaType.APPLICATION_JSON).put(Entity.json(bus));
		System.out.println("Response Http Status: " + response.getStatus());
		Bus busVal = response.readEntity(Bus.class);
		System.out.println(busVal.getId()+", "+ busVal.getFrom()+", "+ busVal.getTo());
        
	    client.close();
	}
	
	public static void addBus(Bus bus) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8091/transport/bus");
		WebTarget update = base.path("add");
		Response response = update.request(MediaType.APPLICATION_JSON).post(Entity.json(bus));
		System.out.println("Response Http Status: "+ response.getStatus());
        System.out.println(response.getLocation());
	    client.close();
	}

	public static void main(String[] args) {

		Bus bus = new Bus();
		
		// getAllBus();
		// getBusById(101);
		//bus.setId(103);
		bus.setFrom("Borivali");
		bus.setTo("Dadar");
		addBus(bus) ;
		//updateBus(bus) ;
	}

}
