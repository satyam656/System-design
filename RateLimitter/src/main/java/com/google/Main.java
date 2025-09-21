package com.google;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Rate Limiter Service....");

        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocations(new String[] {
                "classpath:dispatcherServlet.xml",
                "classpath:applicationContext.xml"
        });

        // Setup DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        // Jetty server on port 8080
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(new ServletHolder(dispatcherServlet), "/*");

        server.start();
        System.out.println("Server running at http://localhost:8080");
        server.join();
    }
}