package com.marcosoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender {
    private Socket socket;
    private PrintWriter output;

    public MessageSender(String hostname, int port) {
        try {
            socket = new Socket(hostname, port);
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
