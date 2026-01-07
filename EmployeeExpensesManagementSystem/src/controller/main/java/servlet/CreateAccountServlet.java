package servlet;

import java.io.IOException;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;

@WebServlet("/CreateAccount")
public class CreateAccountServlet extends HttpServlet {

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Check email exists
        if (employeeDAO.isEmailExists(email)) {
            request.setAttribute("error", "Email already registered");
            request.getRequestDispatcher("CreateAccount.jsp")
                   .forward(request, response);
            return;
        }

        // Create employee object
        Employee emp = new Employee();
        emp.setName(name);
        emp.setDepartment(department);
        emp.setEmail(email);
        emp.setPhone(phone);
        emp.setPassword(password); // plaintext for assignment

        // Register employee (ONLY ONCE)
        boolean success = employeeDAO.registerEmployee(emp);

        if (success) {
            // Redirect back to JSP with success flag
            response.sendRedirect(
                request.getContextPath() +
                "/CreateAccount.jsp?success=true"
            );
        } else {
            request.setAttribute("error", "Registration failed");
            request.getRequestDispatcher("CreateAccount.jsp")
                   .forward(request, response);
        }
    }
}

