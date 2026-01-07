package servlet;

import java.io.IOException;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Employee;

@WebServlet("/login")
public class Login extends HttpServlet {

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Employee emp = employeeDAO.login(email, password);

        if (emp != null) {
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("employee", emp);
            session.setAttribute("role", role);

            // Redirect to Home
            response.sendRedirect("Home.html");

        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("Login.jsp")
                   .forward(request, response);
        }
    }
}

