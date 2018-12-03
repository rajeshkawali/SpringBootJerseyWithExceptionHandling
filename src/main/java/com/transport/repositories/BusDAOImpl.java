package com.transport.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.transport.model.Bus;

@Transactional
@Repository
public class BusDAOImpl implements BusDAO {

	@PersistenceContext
	private EntityManager entityManager;

	// Session session = entityManager.unwrap(Session.class);

	@Override
	public Bus getBusById(Integer id) {
		return entityManager.find(Bus.class, id);
	}

	@Override
	public void addBus(Bus bus) {
		entityManager.persist(bus);
	}

	@Override
	public void updateBus(Bus bus) {
		Bus busData = getBusById(bus.getId());
		busData.setFrom(bus.getFrom());
		busData.setTo(bus.getTo());
		entityManager.flush();
	}

	@Override
	public void deleteBus(Integer id) {
		entityManager.remove(getBusById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bus> getAllBus() {
		String hql = "FROM Bus as b ORDER BY b.id";
		return (List<Bus>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public boolean busExists(String from, String to) {
		System.out.println("from=" + from);
		System.out.println("to=" + to);
		
		String hql = "FROM Bus as b WHERE b.from = :from";
		int count = entityManager.createQuery(hql).setParameter("from", from).getResultList().size();

		//String hql = "SELECT * FROM Bus as b WHERE b.from =:from and b.to =:to";
		//int count = entityManager.createNativeQuery(hql).setParameter("from", from).setParameter("to", to).getResultList().size();

		return count > 0 ? true : false;
	}

}
