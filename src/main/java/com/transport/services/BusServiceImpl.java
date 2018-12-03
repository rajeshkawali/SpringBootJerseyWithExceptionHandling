package com.transport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.model.Bus;
import com.transport.repositories.BusDAO;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusDAO busDAO;

	@Override
	public Bus getBusById(Integer id) {
		Bus obj = busDAO.getBusById(id);
		return obj;
	}

	public synchronized boolean addBus(Bus bus) {
		if (busDAO.busExists(bus.getFrom(), bus.getTo())) {
			return false;
		} else {
			busDAO.addBus(bus);
			return true;
		}
	}

	@Override
	public void updateBus(Bus bus) {
		busDAO.updateBus(bus);
	}

	@Override
	public void deleteBus(Integer id) {
		busDAO.deleteBus(id);
	}

	@Override
	public List<Bus> getAllBus() {
		return busDAO.getAllBus();
	}

}
