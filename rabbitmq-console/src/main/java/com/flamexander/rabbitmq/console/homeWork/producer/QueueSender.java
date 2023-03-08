package com.flamexander.rabbitmq.console.homeWork.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueSender {
    private final static String EXCHANGE_NAME = "ItBlog";
    private Channel channel;

    public QueueSender() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        System.out.println("[*] Enter your messages");
    }

    public void send(String theme, String article) throws IOException {
        channel.basicPublish(EXCHANGE_NAME, theme, null, article.getBytes());
    }
}
