package com.transport.services;

import java.util.List;

import com.transport.model.Bus;

public interface BusService {

	Bus getBusById(Integer id);

	boolean addBus(Bus bus);

	void updateBus(Bus bus);

	void deleteBus(Integer id);

	List<Bus> getAllBus();

}
