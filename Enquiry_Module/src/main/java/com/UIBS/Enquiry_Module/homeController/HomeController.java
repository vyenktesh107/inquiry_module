package com.UIBS.Enquiry_Module.homeController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UIBS.Enquiry_Module.homeRepositry.EnquiryRepository;
import com.UIBS.Enquiry_Module.model.Email;
import com.UIBS.Enquiry_Module.model.Enquiry;
import com.UIBS.Enquiry_Module.serviceI.HomeServiceI;
import com.UIBS.Enquiry_Module.utility.EmailSenderUtility;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
	
	@Autowired
	HomeServiceI hsi;
	
	@Autowired
	EmailSenderUtility esu;
	
	@Autowired
	EnquiryRepository enquiryRepository;
	
//	@Autowired
//    private ScheduledExecutorService scheduledExecutorService;
//	
//	@Autowired
//    private TaskScheduler taskScheduler;
//
//    private ScheduledFuture<?> reminderTask;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
//	@SuppressWarnings("deprecation")
	@PostMapping(value="/saveEnquiry")
	public ResponseEntity<Enquiry> postEnquiry(@Valid @RequestBody Enquiry e, Email email){
		
		email.setFromMail(fromMail);
		log.info("post method working");
		Enquiry eq=hsi.saveEnquiry(e);
//		log.info("result" + e);
		System.out.println("OKAY");
		
		
//		taskScheduler.schedule(
//	                () -> esu.sendReminderEmails(new Email()),
//	                new Date(System.currentTimeMillis() + 60 * 1000)
//	        );
		esu.sendReminderEmails(email);
		System.out.println("DONE");
		
		System.out.println(email.getFromMail());
		return new ResponseEntity<Enquiry>(eq,HttpStatus.CREATED);
	
//	reminderTask = scheduledExecutorService.scheduleAtFixedRate(() -> {
//        Date oneMinuteAgo = new Date(System.currentTimeMillis() - 1 * 60 * 1000);
//        List<Enquiry> pendingInquiries = enquiryRepository.findBySubmissionDate(oneMinuteAgo);
//        for (Enquiry inquiry : pendingInquiries) {
//            // If user hasn't applied for a loan, send reminder email
//            if (!inquiry.isLoanApplied()) {
//                String email = inquiry.getEmail();
//                esu.sendReminderEmails(new Email());
//            }
//        }
//    }, 60000, 60000, TimeUnit.MILLISECONDS); // Delayed execution after 1 minute, repeat every 1 minute
		
	}
	
	@GetMapping("/getEnquiry")
	public ResponseEntity<Iterable<Enquiry>> getEnquiry(){
		log.info("get method executed");
		Iterable<Enquiry> eq = hsi.getEnquiry();
		log.info("enquiry get method execution complete");
		return new  ResponseEntity<Iterable<Enquiry>>(eq, HttpStatus.OK);
	}
	
	
//	@Autowired
//    private TaskScheduler taskScheduler;
//
//    @Autowired
//    private EnquiryRepository inquiryRepository;
//
//    private ScheduledFuture<?> reminderTask;
//
//    @PostMapping("/saveEnquiry")
//    public void submitInquiry(@RequestBody Enquiry inquiry) {
//        inquiryRepository.save(inquiry);
//
//        // Schedule task to send reminder email after one minute
//        reminderTask = taskScheduler.schedule(
//                () -> sendReminderEmail(inquiry.getEmail()),
//                new Date(System.currentTimeMillis() + 60 * 1000)
//        );
//    }
//
//    @PostMapping("/apply-for-loan")
//    public void applyForLoan() {
//        // Cancel the reminder task if the user applies for a loan within one minute
//        if (reminderTask != null && !reminderTask.isDone()) {
//            reminderTask.cancel(false);
//        }
//        // Handle loan application logic...
//    }
//
//    private void sendReminderEmail(String email) {
//        // Implement email sending logic here
//    }
	
}
