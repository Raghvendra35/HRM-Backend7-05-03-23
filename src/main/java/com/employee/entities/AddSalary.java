package com.employee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AddSalary 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int    salaryId;
	private String employeeName;
	
	private int amount;
	private String months;
	private double totalSalary;
	private double inHandSalary;
	private int    workingDaysInMonths;
	private double pf;
	private double esi;
	private double medicalInsurance;
	
	
	
	
	
	@OneToOne(targetEntity = Employee.class)
	private Employee employee;
	
	
	
	


	
}
