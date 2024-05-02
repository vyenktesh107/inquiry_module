package com.UIBS.Enquiry_Module.model;

import com.UIBS.Enquiry_Module.mail.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	
	private String toMail;
	private String fromMail;
	private String textMessage;
	private String subject;
}
