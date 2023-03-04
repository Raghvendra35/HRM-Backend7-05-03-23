package com.employee.dao;

import java.util.List;
import java.util.Map;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.employee.entities.*;


public interface EmployeeRepository  extends JpaRepository<Employee, Integer>
{

@Query(value= "SELECT employee_id as employeeId,first_name as firstName,email_id as emailId from employee",nativeQuery = true)
	public List<Map<String, Object>> findNameAndEmail();


//Search query
@Query("SELECT emp from employee as emp where CONCAT(emp.firstName,emp.emailId,emp.designation) LIKE %?1%")
public List<Employee> search(String keyword);






//Login Page 
@Query(value="SELECT count(*) from employee where email_id=? and password=?", nativeQuery= true)
public int loginPage(String emailId, String password);
 

}
//Search data from two table 
//@Query("SELECT ")
