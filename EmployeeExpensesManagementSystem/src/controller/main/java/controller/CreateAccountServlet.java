package controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class CreateAccountServlet extends HttpServlet {

    private static final String DB_URL =
        "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "oracle";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO EMPLOYEE "
                       + "(NAME, DEPARTMENT, EMAIL, PHONE, ADDRESS, PASSWORD) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(6, password); // plaintext for assignment

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect("Login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error!");
            request.getRequestDispatcher("CreateAccount.jsp").forward(request, response);
        }
    }
}
