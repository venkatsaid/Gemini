package com.bankgemini.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {


	@Autowired
    private JavaMailSender sender;
	
	@PostMapping("/sendemail/")
    @ResponseBody
    String home(@RequestParam(name = "id") String email,String user,String pass) {
        try {
            sendEmail(email,user,pass);
            System.out.println("email sent");
            return "Email Sent!";
        }catch(Exception ex) {
        	System.out.println("error in sending");
            return "Error in sending email: "+ex;
        }
    }
 
    private void sendEmail(String email,String userName,String password) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo(email);
        helper.setText("Login credentials to login to bank service \n "+"user Name : "+ userName+"\n password: "+password);
        helper.setSubject("Welcome to the bank gemini!");
         
        sender.send(message);
    }
}
