package com.UIBS.Enquiry_Module.mail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Notification {

	public String msg;
	
	public Notification() {
		log.info("inside notification "+msg);
		this.msg = "We saw you recently inquired on our UIBS site but have not yet applied for a loan. "
				+ "Click on the link below to apply for a loan.";
	}
}
