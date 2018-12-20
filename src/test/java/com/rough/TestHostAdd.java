package com.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.utilities.MonitoringMail;
import com.utilities.TestConfig;

public class TestHostAdd {

	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		MonitoringMail mail = new MonitoringMail();
		String MessageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/ProTrackplus1/HTML_20Report/";
		
		System.out.println(MessageBody);
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, MessageBody);

	}

}
