package com.test;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.naming.InitialContext;

public class CreateConnections {

	public static void main(String[] args) {
		
		final int connectionsAllowed = 100;
		final int sessionsPerConnection = 1;
		
		try {
			// Lookup for "java.naming.factory.initial" parameter configured from jndi.properties
			InitialContext initialContext = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
			
			// Create list of connections and sessions
			List<Connection> connections = new ArrayList<>();
			List<Session> sessions = new ArrayList<>();

			// Create connections
			for (int c = 0; c < connectionsAllowed; c++) {
				Connection conn = cf.createConnection();
				conn.start();
				connections.add(conn);
				System.out.println("Connection created: " + (c + 1));

				// Create sessions
				for (int s = 0; s < sessionsPerConnection; s++) {
					// Create a session with client-acknowledge
					Session session = conn.createSession(false, Session.CLIENT_ACKNOWLEDGE);
					sessions.add(session);
				}
			}
			
			//Sleep for 1 hour
			Thread.sleep(3600000);
			
			for (Session session : sessions) {
				session.close();
			}
			
			for (Connection connection : connections) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
