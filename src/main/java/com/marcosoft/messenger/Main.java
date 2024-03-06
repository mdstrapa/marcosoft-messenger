package com.marcosoft.messenger;


import java.util.Scanner;

public class Main {

    //private static volatile boolean isListenerActive = true;

    public static void main(String[] args) {
        System.out.println("Marcosoft Messenger =================================");
        System.out.println();

        // starting the listener
        Runnable listenerTask = () -> {
            //while(isListenerActive){
                MessageListener messageListener = new MessageListener(9999);
                messageListener.listen();
            //}
        };

        Thread listenerThread = new Thread(listenerTask);
        listenerThread.start();

        // configuring the sender parameters
        String ipDestination;
        String userName;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Type your name: " );
        userName = scanner.nextLine();
        System.out.print("Type the IP of the destination machine: " );
        ipDestination = scanner.nextLine();

        System.out.println();

        String userMessage;

        System.out.println("Dialog: (type 'QUIT' to leave the conversation)");
        userMessage = scanner.nextLine();
        while (!userMessage.equals("QUIT")) {

            MessageSender messageSender = new MessageSender(ipDestination, 9999);
            messageSender.sendMessage(userName + ": " + userMessage);
            messageSender.closeConnection();

            userMessage = scanner.nextLine();
        }

        //isListenerActive = false;

        listenerThread.interrupt();

        scanner.close();

    }
}