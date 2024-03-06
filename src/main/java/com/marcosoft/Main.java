package com.marcosoft;


import java.util.Scanner;

public class Main {

    private static volatile boolean isListnerActive = true;

    public static void main(String[] args) {
        System.out.println("Marcosoft Messenger");
        System.out.println();

        // starting the listner
        Runnable listnerTask = () -> {
            while(isListnerActive){
                MessageListener messageListener = new MessageListener(9999);
                messageListener.listen();
            }
        };

        Thread listnerThread = new Thread(listnerTask);
        listnerThread.start();

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

        System.out.println("Dialog:");
        userMessage = scanner.nextLine();
        while (!userMessage.equals("QUIT")) {

            MessageSender messageSender = new MessageSender(ipDestination, 9999);
            messageSender.sendMessage(userName + ": " + userMessage);
            messageSender.closeConnection();

            //System.out.print("Next message or QUIT to exit: ");
            userMessage = scanner.nextLine();
        }

        isListnerActive = false;
        try {
            listnerThread.join();
            listnerThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scanner.close();

    }
}