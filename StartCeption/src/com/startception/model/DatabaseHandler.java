package com.startception.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * This class handles database transactions and connection
 * @author Erik Sandstrom
 * */
public class DatabaseHandler {
	private final String PERSISTENCE_UNIT_NAME = "StartCeption";
	private EntityManagerFactory factory;

	public DatabaseHandler() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	
	/**
	 * This method verifies if the database contains a client that has the same email and
	 * password
	 * @param email the email to be verified
	 * @param password the password to be verified
	 * @return true if the client is already in the database, false otherwise
	*/
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
	
	/**
	 * This method creates a client object and saves it in the database if the email and the password is not saved
	 * in the database already
	 * @param email the email to be added to the client object
	 * @param password the password to be to the client object
	 * @return true if the client is successfully added to the database, false otherwise
	*/
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
