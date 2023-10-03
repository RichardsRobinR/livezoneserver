package org.rdx;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebSocket
public class MyWebSocketHandler {

    private static final List<Session> sessions = new ArrayList<>();


    void displaySessions() {
        System.out.println("session" + sessions);
    }

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        sessions.add(session);
        System.out.println("WebSocket connected: " + session.getRemoteAddress());
        broadcast("User " + session.getRemoteAddress() + "<->" + "connected");


//        displays sessions
//        displaySessions();
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("Received message from client: " + message);
        broadcast("User " + session.getRemoteAddress() + "<->" + message);

    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) throws IOException {
        sessions.remove(session);
        System.out.println("WebSocket closed. StatusCode: " + statusCode + ", Reason: " + reason);
        broadcast("User disconnected: " + session.getRemoteAddress());
    }

    @OnWebSocketError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket error: " + throwable.getMessage());
    }

    private void broadcast(String message) {
        try {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    session.getRemote().sendString(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
