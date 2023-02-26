package com.employee.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryRequest 
{

	private String employeeName;
	private int employeeId;
	
	private int amount;
	private String months;
	private double totalSalary;
	private double inHandSalary;
	private int    workingDaysInMonths;
	private double pf;
	private double esi;
	private double medicalInsurance;
	private double salaryDeducted;	
	
	

}
