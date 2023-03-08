package com.flamexander.rabbitmq.console.homeWork.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueReceiver {
    private final static String EXCHANGE_NAME = "ItBlog";
    private final Channel channel;
    private String queueName;

    public QueueReceiver() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        channel = connection.createChannel();
        queueName = channel.queueDeclare().getQueue();

        System.out.println("My queue name: " + queueName);
        System.out.println(" [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received ");
            System.out.println(message);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
        });

    }

    public void subscribe(String theme) throws IOException {
        channel.queueBind(queueName, EXCHANGE_NAME, theme);
    }

    public void unsubscribe(String theme) throws IOException {
        channel.queueUnbind(queueName, EXCHANGE_NAME, theme);
    }
}
