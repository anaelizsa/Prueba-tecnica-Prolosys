package com.employeeManagement.employeeManagement.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String id) {
        super("Empleado con id:" + id + " no se encuentra");
    }
}
