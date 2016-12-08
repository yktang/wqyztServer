package com.weiwei.rabbitmq.logs;

import java.io.IOException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class ReceiveLogs {
	private static final String EXCHANGE_NAME = "logs";
	private static final String EXCHANGE_NAME2= "logs2";
	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException {
		getPush();
		//getPush2();
	}

	public static void getPush() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		System.out.println("进来1");
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		Channel channel1 = connection.createChannel();
		channel1.exchangeDeclare(EXCHANGE_NAME2, "fanout");
		String queueName1 = channel1.queueDeclare().getQueue();
		channel1.queueBind(queueName1, EXCHANGE_NAME2, "");
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());

			System.out.println(" [x] Received '" + message + "'");
		}
	}
	
	public static void getPush2() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		System.out.println("进来2");
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());

			System.out.println(" [x] Received '" + message + "'");
		}
	}
}
