package com.employeeManagement.employeeManagement.repository;

import com.employeeManagement.employeeManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, String> {

    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING(numero_empleado, 2) AS UNSIGNED)), 0) FROM empleado", nativeQuery = true)
    int findMaxEmployeeNumber();
}
