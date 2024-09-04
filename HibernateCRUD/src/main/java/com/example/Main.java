package com.example;

import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Scanner scanner = new Scanner(System.in);

        //logica este la fel pentru toate clasele

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Get Employee by ID");
            System.out.println("5. Get Employees");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Employee newEmployee = new Employee();
                    System.out.print("Enter Employee First Name: ");
                    newEmployee.setFirstName(scanner.nextLine());
                    System.out.print("Enter Employee Last Name: ");
                    newEmployee.setLastName(scanner.nextLine());
                    System.out.print("Enter Employee Position: ");
                    newEmployee.setPosition(scanner.nextLine());
                    employeeDAO.create(newEmployee);
                    System.out.println("Employee added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Employee ID to delete: ");
                    Long deleteId = scanner.nextLong();
                    Employee employeeToDelete = employeeDAO.findById(deleteId);
                    if (employeeToDelete != null) {
                        employeeDAO.delete(employeeToDelete);
                        System.out.println("Employee deleted successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID to update: ");
                    Long updateId = scanner.nextLong();
                    scanner.nextLine();  // Consume newline
                    Employee employeeToUpdate = employeeDAO.findById(updateId);
                    if (employeeToUpdate != null) {
                        System.out.print("Enter Employee First Name: ");
                        employeeToUpdate.setFirstName(scanner.nextLine());
                        System.out.print("Enter Employee Last Name: ");
                        employeeToUpdate.setLastName(scanner.nextLine());
                        System.out.print("Enter Employee Position: ");
                        employeeToUpdate.setPosition(scanner.nextLine());
                        employeeDAO.update(employeeToUpdate);
                        System.out.println("Employee updated successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Employee ID: ");
                    Long getId = scanner.nextLong();
                    Employee employee = employeeDAO.findById(getId);
                    if (employee != null) {
                        System.out.println("Employee Details: ");
                        System.out.println("ID: " + employee.getId());
                        System.out.println("First Name: " + employee.getFirstName());
                        System.out.println("Last Name: " + employee.getLastName());
                        System.out.println("Position: " + employee.getPosition());
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 5:
                    List<Employee> employees = employeeDAO.findAll();
                    System.out.println("Employee List:");
                    for (Employee emp : employees) {
                        System.out.println("ID: " + emp.getId() + ", First Name: " + emp.getFirstName()
                                + ", Last Name: " + emp.getLastName() + ", Position: " + emp.getPosition());
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
