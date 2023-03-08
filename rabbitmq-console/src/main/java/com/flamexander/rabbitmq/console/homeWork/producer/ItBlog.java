package com.flamexander.rabbitmq.console.homeWork.producer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class ItBlog {
    public static void main(String[] args) throws IOException, TimeoutException {
        QueueSender sender = new QueueSender();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String consoleMessage;
            String article;
            String theme;

            while (!(consoleMessage = bufferedReader.readLine()).equals("ESQ")) {
                String[] articleAndTheme = consoleMessage.split(" ", 2);

                theme = articleAndTheme[0];
                article = articleAndTheme[1];

                sender.send(theme, article);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
