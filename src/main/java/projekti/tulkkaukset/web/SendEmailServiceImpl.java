package projekti.tulkkaukset.web;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendEmailServiceImpl {


	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("noreply.qwerty.pro@gmail.com");
		mailSender.setPassword("skyqkvnkvjbpfvdd");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}

	
	public void sendEmail(String to, String subject, String text) {
		MimeMessage message = getJavaMailSender().createMimeMessage();
	         	    
	    try {
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    	helper.setFrom("janica.fagerblom@gmail.com");
		    helper.setTo(to);
		    helper.setSubject(subject);
			helper.setText(text);
			getJavaMailSender().send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	    
	}

}