package com.UIBS.Enquiry_Module.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.UIBS.Enquiry_Module.homeRepositry.EnquiryRepository;
import com.UIBS.Enquiry_Module.model.Email;
import com.UIBS.Enquiry_Module.model.Enquiry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailSenderUtility {

	@Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private JavaMailSender sender;

    @Scheduled(fixedRate = 60000) // Run every 10 minutes (in milliseconds)
    public void sendReminderEmails(Email e) {
    	
    	System.out.println(e.getFromMail());
        // Find inquiries submitted in the last 10 minutes
        Date tenMinutesAgo = new Date(System.currentTimeMillis() - 10 * 60 * 1000);
    	Date oneMinuteAgo = new Date(System.currentTimeMillis() - 60 * 1000);
        List<Enquiry> recentInquiries = enquiryRepository.findAll();
        List<Enquiry> pendingInquiries = enquiryRepository.findBySubmissionDate(oneMinuteAgo);
        
        System.out.println(recentInquiries);
        System.out.println(pendingInquiries);
        System.out.println("c");
        
        
        	System.out.println("zzz");
        // Send reminder email for each inquiry
        for (Enquiry enquiry : pendingInquiries) {
        	System.out.println("a");
            if (!enquiry.isLoanApplied()) {
            	System.out.println("b");
            	sendEnquirySuccessMail(enquiry.getEmail(), e.getFromMail(), "Reminder: Apply for a Loan",
                        "Dear " + enquiry.getEnquiryName() + ",\n\n"
                                + "Thank you for your inquiry. If you're still interested in a loan, please apply at your earliest convenience.\n\n"
                                + "Best regards,\nYour Lending Team");
            	
            	System.out.println(e.getFromMail());
            }
            System.out.println("MAIL SENT");
            
        }
        
        
    }


	private void sendEnquirySuccessMail(String to, String from, String subject, String text) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
	}


//	public void sendEnquirySuccessMail(Email e) {
//		
//		SimpleMailMessage m = new SimpleMailMessage();
//		m.setTo(e.getToMail());
//		m.setFrom(e.getFromMail());
//		m.setSubject(e.getSubject());
//		m.setText(e.getTextMessage());
//		
//		sender.send(m);
//		log.info("Mail sent");
//	}
}
