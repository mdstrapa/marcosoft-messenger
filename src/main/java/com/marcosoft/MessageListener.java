package com.marcosoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageListener {
    private ServerSocket serverSocket;

    public MessageListener(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String message = reader.readLine();
                handleIncomingMessage(message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleIncomingMessage(String message) {
        System.out.println(message);
    }
}
