package com.marcosoft;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Marcosoft Messager");
        System.out.println();


        Scanner scanner = new Scanner(System.in);

        System.out.print("Please type 1 to send or 2 to listen: ");
        String userInput = scanner.nextLine();

        if(userInput.equals("1")){
            MessageSender messageSender = new MessageSender("localhost", 9999);
            messageSender.sendMessage("Hello, network application!");
            messageSender.closeConnection();
        }else if(userInput.equals("2")){
            MessageListener messageListener = new MessageListener(9999);
            messageListener.listen();
        }

    }
}