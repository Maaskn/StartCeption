package com.startception.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.startception.model.DatabaseHandler;

/**
 * Application Lifecycle Listener implementation class ServContListener
 *
 */
@WebListener
public class ServContListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServContListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	ServletContext svCont = event.getServletContext();
    	
    	synchronized(svCont){
    		DatabaseHandler dbHandler = (DatabaseHandler)svCont.getAttribute("dbHandler");
    		if(dbHandler != null)dbHandler = null;
    	}
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	ServletContext svCont = event.getServletContext();
    	
    	synchronized(svCont){
    		DatabaseHandler dbHandler = new DatabaseHandler();
    		svCont.setAttribute("dbHandler", dbHandler);
    	}
    }
	
}
