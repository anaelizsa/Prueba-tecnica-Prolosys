package com.employeeManagement.employeeManagement.service;

import com.employeeManagement.employeeManagement.exception.EmployeeNotFoundException;
import com.employeeManagement.employeeManagement.model.Employee;
import com.employeeManagement.employeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    //Instanciar repository para usar crud
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee){
        int lastNumber = employeeRepository.findMaxEmployeeNumber();
        String newNumber = String.format("E%04d", lastNumber + 10);
        employee.setEmployeeNumber(newNumber);
        return employeeRepository.save(employee);
    }

    public List<Employee> listAll(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(String id){
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void deleteEmployee(String id){
        if (employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public Employee updateEmployee(Employee newEmployee, String id){
        return employeeRepository.findById(id)
                .map(employeeMap ->{
                    employeeMap.setName(newEmployee.getName());
                    employeeMap.setPaternalSurname(newEmployee.getPaternalSurname());
                    employeeMap.setMaternalSurname(newEmployee.getMaternalSurname());
                    employeeMap.setCompany(newEmployee.getCompany());
                    employeeMap.setGender(newEmployee.getGender());
                    employeeMap.setBirthdate(newEmployee.getBirthdate());
                    employeeMap.setCurp(newEmployee.getCurp());
                    employeeMap.setRfc(newEmployee.getRfc());
                    return employeeRepository.save(employeeMap);
                }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
