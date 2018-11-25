package com.spring.boot.activemq;


import org.springframework.jms.core.MessageCreator;
import sun.plugin2.message.Message;

import javax.jms.JMSException;
import javax.jms.Session;

public class Msg implements MessageCreator {
	
	@Override
	public Message createMessage(Session session) throws JMSException {
		return session.createTextMessage("测试消息");
	}
	
}
