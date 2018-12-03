package com.transport.repositories;

import java.util.List;

import com.transport.model.Bus;

public interface BusDAO {

	Bus getBusById(Integer id);
	void addBus(Bus bus);
	void updateBus(Bus bus);
	void deleteBus(Integer id);
	List<Bus> getAllBus();
	boolean busExists(String from, String to);
}
