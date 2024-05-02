package com.UIBS.Enquiry_Module.serviceI;

import com.UIBS.Enquiry_Module.model.Enquiry;

public interface HomeServiceI {

	public Enquiry saveEnquiry(Enquiry e);

	public Iterable<Enquiry> getEnquiry();

}
