package com.weiwei.rabbitmq.logs;

import java.io.IOException;




import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {
	
	 private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String[] args) throws Exception {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		String message = "你好";
		
		channel.basicPublish(EXCHANGE_NAME, "black", null, message.getBytes());
		
		channel.close();
        connection.close();
	}
}
