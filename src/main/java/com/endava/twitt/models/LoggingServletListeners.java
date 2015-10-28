package com.endava.twitt.models;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

@Component
public class LoggingServletListeners {

	private static Logger logger = LoggerFactory.getLogger(LoggingServletListeners.class);
	public static void main(String[] args) {
		SpringApplication.run(LoggingServletListeners.class, args);

	}

	@Bean
	public DispatcherServlet myServlet() {
		logger.debug("DispatcherServlet run. " + DispatcherServlet.class);
		return new DispatcherServlet() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -7937906908079602461L;
				
		};
	}

	@Bean
	    public ContextLoaderListener myListener() {
		
	        return new ContextLoaderListener() {

				@Override
				public void contextDestroyed(ServletContextEvent arg0) {
					// TODO Auto-generated method stub
					logger.debug("ContextLoaderListener Destroyed.");
				}

				@Override
				public void contextInitialized(ServletContextEvent arg0) {
					// TODO Auto-generated method stub
					logger.debug("ContextLoaderListener Initialized.");
				}

	           
	        };
	    }
	
	

   
}
