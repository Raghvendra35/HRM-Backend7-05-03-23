package com.employee.service;

import java.util.List;
import java.util.Optional;

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
	double inHandSalary=totalSalary-pf-esi-medicalInsurance;
	System.out.println(inHandSalary);

	 
	 addSalary.setMonths(salaryRequest.getMonths());
	 addSalary.setEmployee(employee);
	 addSalary.setWorkingDaysInMonths(workingDays);
	 addSalary.setMedicalInsurance(medicalInsurance);
	 addSalary.setTotalSalary(totalSalary);
	 addSalary.setPf(pf);
	 addSalary.setEsi(esi);
	 addSalary.setInHandSalary(inHandSalary);
	 
	 AddSalary salary=this.addSalaryRepository.save(addSalary);
	   return salary;
		
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
	
	
		
	
	

}
