package com.UIBS.Enquiry_Module.homeRepositry;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.UIBS.Enquiry_Module.model.Enquiry;

import jakarta.transaction.Transactional;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long>{

//	public List<Enquiry> findBySubmissionDateAfter(Date tenMinutesAgo);
	
//	@Transactional
	 @Query("SELECT i FROM Enquiry i WHERE i.submissionDate > :date")
	  public List<Enquiry> findBySubmissionDate(@Param(value = "date") Date d);
	
//	 @Query("SELECT i FROM Enquiry i WHERE i.submissionDate > :twoMinutesAgo AND i.loanApplied = false")
//	    List<Enquiry> findPendingInquiries(Date twoMinutesAgo);

}
