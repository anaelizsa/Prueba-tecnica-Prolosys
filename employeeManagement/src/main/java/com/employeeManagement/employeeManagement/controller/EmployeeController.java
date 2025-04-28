package com.employeeManagement.employeeManagement.controller;

import com.employeeManagement.employeeManagement.exception.EmployeeNotFoundException;
import com.employeeManagement.employeeManagement.model.Employee;
import com.employeeManagement.employeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> listAllEmployees(){
        return employeeService.listAll();
    }

    @PostMapping("/add-employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(newEmployee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable String id){
        try {
            return ResponseEntity.ok(employeeService.findEmployeeById(id));
        } catch (EmployeeNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee){
        try {
            return ResponseEntity.ok(employeeService.updateEmployee(updatedEmployee, id));
        }catch (EmployeeNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable String id){
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (EmployeeNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
