package com.UIBS.Enquiry_Module.homeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UIBS.Enquiry_Module.CustomeExceptionHandler.EnquiryNotSubmitException;
import com.UIBS.Enquiry_Module.homeRepositry.EnquiryRepository;
//import com.UIBS.Enquiry_Module.homeRepositry.EnquiryRepositry;
import com.UIBS.Enquiry_Module.model.Enquiry;
import com.UIBS.Enquiry_Module.serviceI.HomeServiceI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HomeServiceImpl implements HomeServiceI{
	
	@Autowired
	EnquiryRepository er;

	@Override
	public Enquiry saveEnquiry(Enquiry e) {
		log.info("Enquiry save executed");
		
		List<String> li = new ArrayList<String>();
		List<Enquiry> al = er.findAll();
		
		for(Enquiry a:al) {
			li.add(a.getPancardNo());
		}
		
		if(li.contains(e.getPancardNo())) {
			log.info("EnquiryNotSubmitException Started "+e.getPancardNo()+ "user already exist");
		
	throw new EnquiryNotSubmitException("This Pan card is already stored in our database. Please enter again");
		}
		else {
			return er.save(e);
		}
		
	}

	@Override
	public Iterable<Enquiry> getEnquiry() {
		log.info("Enquiry get executed");
		return er.findAll();
	}

}
