package com.UIBS.Enquiry_Module.model;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Size(min = 3, max=50, message="Enter your full name and must be of min 4 characters" )
	private String enquiryName;
	
	@Min(18)
	private int age;
	
	@NotEmpty
	@Email(message = "Email address is not valid")
	private String email;
	
	//@Pattern(regexp = "^[789][0-9]{9}$", message = "Enter only Number and 10 digit ")
	private long mobileNo;
	
	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Pan Card Must be like Ex. ABCDE1234Q")
	private String pancardNo;
	
	@NotEmpty
	private String remark;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "submission_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date submissionDate;

    @Column(name = "loan_applied")
    private boolean loanApplied;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CibilScore cibilScore;
	
	@PrePersist
    protected void onCreate() {
        this.submissionDate = new Date(); // Set the date to the current date
    }
}
