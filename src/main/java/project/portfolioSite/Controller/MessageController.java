package project.portfolioSite.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import project.portfolioSite.Entity.Message;
import project.portfolioSite.Service.EmailService;

@RestController
@RequestMapping("/contact")
public class MessageController {
	
	@Autowired
	private EmailService emailService;
	
	@Value("${inbox.email}")
	private String inboxEmail;

	@PostMapping("/submit")
	public RedirectView emailMe (@ModelAttribute("message") Message message, RedirectAttributes ra) throws IOException {

	
		SimpleMailMessage emailMessage = new SimpleMailMessage();
		
		emailMessage.setFrom(message.getEmail());
		emailMessage.setTo(inboxEmail);
		emailMessage.setSubject("Message from Portfolio Website!");
		emailMessage.setText("From: " + message.getEmail() +"\nName: " +message.getName()+ "\n\n" +message.getMessage());
		
		emailService.send(emailMessage);
		
		ra.addFlashAttribute("message", "Message was sent!");
		return new RedirectView("/");
	}
}



