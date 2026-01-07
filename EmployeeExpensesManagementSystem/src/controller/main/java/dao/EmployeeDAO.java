package dao;

import model.Employee;

public interface EmployeeDAO {

    boolean registerEmployee(Employee employee);

    boolean isEmailExists(String email);
    
    Employee login(String email, String password);


}
