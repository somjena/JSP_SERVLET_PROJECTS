package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import util.DBUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String sql = "SELECT * FROM Users WHERE username = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // In production, use BCrypt to verify hashed passwords
                String storedPassword = rs.getString("password");

                // Simple comparison (replace with proper password hashing)
                if (password.equals(storedPassword)) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getString("full_name"),
                            rs.getString("contact_number")
                    );

                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    // Redirect based on role
                    switch(user.getRole()) {
                        case "student":
                            response.sendRedirect("student_dashboard.jsp");
                            break;
                        case "hr":
                            response.sendRedirect("hr_dashboard.jsp");
                            break;
                        case "placement_admin":
                            response.sendRedirect("admin_dashboard.jsp");
                            break;
                        default:
                            response.sendRedirect("login.jsp?error=invalid_role");
                    }
                } else {
                    response.sendRedirect("login.jsp?error=invalid_credentials");
                }
            } else {
                response.sendRedirect("login.jsp?error=user_not_found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=database_error");
        }
    }
}