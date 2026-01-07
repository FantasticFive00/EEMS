package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Employee;
import util.DatabaseConnection;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM EMPLOYEE WHERE EMAIL = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registerEmployee(Employee emp) {
        String sql = "INSERT INTO EMPLOYEE (NAME, DEPARTMENT, EMAIL, PHONE, PASSWORD) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setString(5, emp.getPassword());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ ADD THIS METHOD (LOGIN)
    @Override
    public Employee login(String email, String password) {

        Employee emp = null;
        String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ? AND PASSWORD = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp = new Employee();
                emp.setEmpId(rs.getInt("EMP_ID"));
                emp.setName(rs.getString("NAME"));
                emp.setEmail(rs.getString("EMAIL"));
                emp.setDepartment(rs.getString("DEPARTMENT"));
                emp.setPhone(rs.getString("PHONE"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }
}
