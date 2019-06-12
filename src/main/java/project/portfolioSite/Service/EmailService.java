package project.portfolioSite.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	//Spring helper class for e-mails
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	
	public void send(SimpleMailMessage message) {
		try {
		mailSender.send(message);
		} catch (MailException ex) {
			ex.printStackTrace();
		}
	}
}
