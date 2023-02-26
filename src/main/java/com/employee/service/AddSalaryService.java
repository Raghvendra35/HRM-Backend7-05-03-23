package com.employee.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.employee.dao.AddSalaryRepository;
import com.employee.dao.EmployeeRepository;
import com.employee.entities.AddSalary;
import com.employee.entities.Employee;
import com.employee.entities.LeaveEmployee;
import com.employee.request.SalaryRequest;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Controller
public class AddSalaryService
{
	@Autowired
	private AddSalaryRepository addSalaryRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	//Add salary
	public AddSalary saveSalary(SalaryRequest salaryRequest)
	{

	System.out.println("salaryRequest.............");
	System.out.println(salaryRequest);
	
	 Employee employee=employeeRepository.findById(salaryRequest.getEmployeeId()).get();	
	 
	 
	 AddSalary addSalary=new AddSalary();
	 
//	 addSalary.setEmployee(employee);
//	 addSalary.setEmployeeName(salaryRequest.getEmployeeName());
//	 addSalary.setMonths(salaryRequest.getMonths());
//	 addSalary.setAmount(salaryRequest.getAmount());
//  AddSalary salary=this.addSalaryRepository.save(addSalary);
	 
	double totalSalary=salaryRequest.getTotalSalary();
	System.out.println("totalSalary : "+totalSalary);
	
	
	double pf=salaryRequest.getPf();
	System.out.println("pf ==>"+pf);
	
	 pf=totalSalary*pf/100;
	 System.out.println(pf);
	 
	double esi=salaryRequest.getEsi();
	System.out.println("esi ==>"+esi);
	
	esi=totalSalary*esi/100;
	System.out.println(esi);
	
	
	double medicalInsurance=salaryRequest.getMedicalInsurance();
	System.out.println(medicalInsurance);

	
	int workingDays=salaryRequest.getWorkingDaysInMonths();
	System.out.println("Working Days==========================");
	System.out.println(workingDays);
	System.out.println("InHand Salary=====================");
	double salary=totalSalary-pf-esi-medicalInsurance;
	System.out.println(salary);
	
	
	
	
	
	String january="january";
	String february="february";
	String march="march";
	String april="april";
	String may="may";
	String june="june";
	String july="july";
	String august="august";
	String september="september";
	String october="october";
	String november="november";
	String december="december";
	
	double perDaySalary;
	double inHandSalaryDeducted;
	double inHandSalary=0;
	String months=salaryRequest.getMonths();
	System.out.println(months);
	
	
	
	if(january.equalsIgnoreCase(months) || march.equalsIgnoreCase(months) || may.equalsIgnoreCase(months) || july.equalsIgnoreCase(months) 
			|| august.equalsIgnoreCase(months) || october.equalsIgnoreCase(months)	|| december.equalsIgnoreCase(months)         )
	{	
		//Working days total in months = 25
	
		
		System.out.println(months);
		perDaySalary=salary/25;
		System.out.println(perDaySalary);
		inHandSalary=perDaySalary*workingDays;
		
		inHandSalaryDeducted=salary-inHandSalary;
		
		System.out.println("Deducted Salary ===> "+inHandSalaryDeducted);
	    System.out.println("InHandSalary --->"+ inHandSalary);
	

	
	
	
	}
	else if(april.equalsIgnoreCase(months) || june.equalsIgnoreCase(months) || september.equalsIgnoreCase(months) || november.equalsIgnoreCase(months)   )
	{
		//Working days total in months = 24
		System.out.println(months);
		perDaySalary=salary/24;
		System.out.println(perDaySalary);
		
		inHandSalary=perDaySalary*workingDays;
		
		System.out.println(inHandSalary);
		System.out.println("Not Matched !!!");
	}
	
	
	else if(february.equalsIgnoreCase(months))
	{
		//Working days total in months = 22 /23
		System.out.println(months);
		perDaySalary=salary/22;
		System.out.println(perDaySalary);
		
		inHandSalary=perDaySalary*workingDays;
		
		System.out.println(inHandSalary);
		
		System.out.println("Not Matched !!!");
	}
	else
	{
		System.out.println("Not Matched !!!");
	}

	 
	
	
	
	
	
	
	
	 addSalary.setMonths(salaryRequest.getMonths());
	 addSalary.setEmployee(employee);
	 addSalary.setWorkingDaysInMonths(workingDays);
	 addSalary.setMedicalInsurance(medicalInsurance);
	 addSalary.setTotalSalary(totalSalary);
	 addSalary.setPf(pf);
	 addSalary.setEsi(esi);
	 addSalary.setInHandSalary(inHandSalary);
	 
	 AddSalary salaryObj=this.addSalaryRepository.save(addSalary);
	   return salaryObj;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//Get Salary By Id
	public Optional<AddSalary> getSalaryById(int salaryId)
	{
	   Optional<AddSalary> salary=this.addSalaryRepository.findById(salaryId);
		return salary;
	}
	
	
	
	
	//Update Salary
	public void updateSalary(AddSalary addSalary, int salaryId)
		{
			addSalary.setSalaryId(salaryId);
			addSalaryRepository.save(addSalary);
			
		}
		
		
	
	
	 //Delete The Salary
		public void deleteSalary(int salaryId)
		{
			addSalaryRepository.deleteById(salaryId);
			
		}
		
	
		
		//Show All Salary
		public List<AddSalary> getAllSalaryDetails()
		{
		 List<AddSalary> list=(List<AddSalary>) this.addSalaryRepository.findAll();
			return list;
		}
	
		
		// Pagination and Sorting 
		public Page<AddSalary> findAllSalaryWithPagination(Pageable page)
		{
			return addSalaryRepository.findAll(page);
			
		}
	
		
		
		
		//Generate Salary Slip PDF file
		
		private Logger logger=LoggerFactory.getLogger(AddSalaryService.class);
	
		public ByteArrayInputStream generateSalarySlipPdf()
		{
			
	      //AddSalary	salaryObj=this.addSalaryRepository.findById(salaryId).get();
		
	       // double totalSalary=salaryObj.getTotalSalary();
			
			
	        
	        
	        
	        
	        
	        logger.info("Created Pdf Started");
			String companyName="PSL";
			
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			
			Document document=new Document();
			
			PdfWriter.getInstance(document, out);

			document.open();
			
			Font titleFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
			Paragraph titlePara=new Paragraph(companyName,titleFont);
			
			document.add(titlePara);
			
			
			
			Font paraFont=FontFactory.getFont(FontFactory.HELVETICA, 18);
			Paragraph paragraph=new Paragraph();
			
			  document.add(paragraph);
			   
			   
			   document.close();
			
			 return new ByteArrayInputStream(out.toByteArray());
		}
		
	
	

}
