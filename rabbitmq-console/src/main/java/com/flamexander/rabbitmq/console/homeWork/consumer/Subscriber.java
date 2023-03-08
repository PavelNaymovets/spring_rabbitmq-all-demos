package com.flamexander.rabbitmq.console.homeWork.consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class Subscriber {
    public static void main(String[] args) throws IOException, TimeoutException {
        QueueReceiver receiver = new QueueReceiver();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String consoleMessage;
            String command;
            String theme;

            while (!(consoleMessage = bufferedReader.readLine()).equals("ESQ")) {
                String[] splitConsoleMessage = consoleMessage.split(" ", 2);

                command = splitConsoleMessage[0];
                theme = splitConsoleMessage[1];

                if (command.equals("set_topic")) {
                    receiver.subscribe(theme);
                    System.out.println("You are subscribed on theme: " + theme);
                }

                if(command.equals("unset_topic")) {
                    receiver.unsubscribe(theme);
                    System.out.println("You are unsubscribed on theme: " + theme);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
