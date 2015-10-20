package com.startception.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseHandler {
	private final String PERSISTENCE_UNIT_NAME = "StartCeption";
	private EntityManagerFactory factory;

	public DatabaseHandler() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	
	//returns true if the client is already in db, false otherwise
	public boolean verifyClient(String email, String password) {
		EntityManager em = factory.createEntityManager();

		Client client = em.find(Client.class, email);
		if (client != null) {
			
			if (client.getPassword().equals(password)) {
				return true;
			}
		}
		
		em.close();
		return false;
	}

	public boolean registerClient(String email, String password) {
		EntityManager em = factory.createEntityManager();

		if (em.find(Client.class, email) == null) {
			Client client = new Client(email, password);
			
			em.getTransaction().begin();
			em.persist(client);
			em.getTransaction().commit();
			em.close();
			
			return true;
		}
		
		em.close();
		return false;
	}

}
