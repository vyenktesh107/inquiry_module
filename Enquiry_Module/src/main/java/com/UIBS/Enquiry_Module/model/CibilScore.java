package com.UIBS.Enquiry_Module.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CibilScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int cibilScore;
	private String cibilScoreDate;
	private String status;
}
