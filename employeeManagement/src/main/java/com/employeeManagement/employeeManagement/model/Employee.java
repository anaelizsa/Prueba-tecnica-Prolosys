package com.employeeManagement.employeeManagement.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "empleado")
public class Employee {

    @Id
    @Column(name = "numero_empleado", length = 5, nullable = false, unique = true)
    private String EmployeeNumber;

    @Column(name = "nombre", length = 50, nullable = false)
    private String name;

    @Column(name = "apellido_paterno", length = 50, nullable = false)
    private String paternalSurname;

    @Column(name = "apellido_materno", length = 50, nullable = false)
    private String maternalSurname;

    @Column(name = "empresa", length = 50, nullable = false)
    private String company;

    @Column(name = "sexo", length = 16, nullable = false)
    private String gender;

    @Column(name = "fecha_de_nacimiento", nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, length = 18)
    private String curp;

    @Column(nullable = false, length = 13)
    private String rfc;

    public Employee() {
    }

    public Employee(String employeeNumber, String name, String paternalSurname, String maternalSurname, String company, String gender, LocalDate birthdate, String curp, String rfc) {
        EmployeeNumber = employeeNumber;
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.company = company;
        this.gender = gender;
        this.birthdate = birthdate;
        this.curp = curp;
        this.rfc = rfc;
    }

    public String getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        EmployeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalSurname() {
        return paternalSurname;
    }

    public void setPaternalSurname(String paternalSurname) {
        this.paternalSurname = paternalSurname;
    }

    public String getMaternalSurname() {
        return maternalSurname;
    }

    public void setMaternalSurname(String maternalSurname) {
        this.maternalSurname = maternalSurname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeNumber='" + EmployeeNumber + '\'' +
                ", name='" + name + '\'' +
                ", paternalSurname='" + paternalSurname + '\'' +
                ", maternalSurname='" + maternalSurname + '\'' +
                ", company='" + company + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", curp='" + curp + '\'' +
                ", rfc='" + rfc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(EmployeeNumber, employee.EmployeeNumber) && Objects.equals(name, employee.name) && Objects.equals(paternalSurname, employee.paternalSurname) && Objects.equals(maternalSurname, employee.maternalSurname) && Objects.equals(company, employee.company) && Objects.equals(gender, employee.gender) && Objects.equals(birthdate, employee.birthdate) && Objects.equals(curp, employee.curp) && Objects.equals(rfc, employee.rfc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EmployeeNumber, name, paternalSurname, maternalSurname, company, gender, birthdate, curp, rfc);
    }
}

