package org.rdx;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.server.config.JettyWebSocketServletContainerInitializer;
import java.time.Duration;

public class JettyWebSocketServer {

    public static void main(String[] args) {
        Server server = new Server(8080);

        var handler = new ServletContextHandler(server, "/");
        server.setHandler(handler);

        JettyWebSocketServletContainerInitializer.configure(handler, (servletContext, container) -> {
            container.setIdleTimeout(Duration.ofMinutes(15L));
            container.addMapping("/", MyWebSocketHandler.class);
        });

        try {
            server.start();
            System.out.println("WebSocket server started.");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

