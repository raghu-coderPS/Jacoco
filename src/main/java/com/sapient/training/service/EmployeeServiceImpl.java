package com.sapient.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.training.data.EmployeeRepository;
import com.sapient.training.entity.Employee;
import com.sapient.training.exception.EmployeeException;

import lombok.extern.slf4j.Slf4j;

//@Slf4j  Applying Spring AOP for logging
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee getEmployeeById(Long id) throws EmployeeException {
		try {
			Optional<Employee> optional= repository.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new EmployeeException("Invalid Employee Id");
			}
		}catch(DataAccessException e) {

			throw new EmployeeException(e.getMessage(),e);
		}
	}

	@Override
	public Employee getEmployeeByName(String name) throws EmployeeException {
		try {
			return repository.findByName(name);
	
		}catch(DataAccessException e) {
			
			throw new EmployeeException(e.getMessage(),e);
		}
	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		try {
			List<Employee> employeeList= repository.findAll();
			if(employeeList.isEmpty()) {
				return employeeList;
			}else {
				throw new EmployeeException("No employees in the database");			
			}
		}catch(DataAccessException e) {
			throw new EmployeeException(e.getMessage(),e);
		}
	}

	@Override
	public boolean exists(String name) throws EmployeeException {
		return (repository.findByName(name)!=null)?true:false;
	}

	@Override
	public Employee save(Employee employee) throws EmployeeException {
		try {
			return repository.save(employee);
		
		}catch(DataAccessException e) {

			throw new EmployeeException(e.getMessage(),e);
		}
	}

	@Override
	public List<Employee> findByName(String name) throws EmployeeException {
		
		return null;
	}

	@Override
	public List<Employee> findByJobAndSalary(String job, Double salary) throws EmployeeException {
		try {			
			List<Employee> employeeList=repository.findByJobAndSalary(job, salary);
			if(employeeList.isEmpty()) {
				return employeeList;
			}else {				
				throw new EmployeeException("No employees in the database with the specified job and salary");
			}
		}catch(DataAccessException e) {
			throw new EmployeeException(e.getMessage(),e);
		}
	}

	@Override
	public List<Employee> findByJobAndSalaryGreaterThan(String job, Double salary) throws EmployeeException {
		try {
			List<Employee> employeeList=repository.findByJobAndSalaryGreaterThan(job, salary);
			if(employeeList.isEmpty()) {
				return employeeList;
			}else {
							
				throw new EmployeeException("No employees in the database with the specified conditions");
			}
		}catch(DataAccessException e) {
		
			throw new EmployeeException(e.getMessage(),e);
		}
	}


}
