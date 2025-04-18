package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import util.DBUtil;
import util.PasswordHasher;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Admin verification code (in production, store this securely)
    private static final String ADMIN_VERIFICATION_CODE = "CAMPUS123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String fullName = request.getParameter("fullname");
        String contact = request.getParameter("contact");

        // Basic validation
        if (!password.equals(confirmPassword)) {
            response.sendRedirect("register.jsp?error=Passwords do not match");
            return;
        }

        // Special validation for admin registration
        if ("placement_admin".equals(role)) {
            String adminCode = request.getParameter("admin-code");
            if (!ADMIN_VERIFICATION_CODE.equals(adminCode)) {
                response.sendRedirect("register.jsp?error=Invalid admin verification code");
                return;
            }
        }

        try (Connection conn = DBUtil.getConnection()) {
            // Check if username or email already exists
            if (userExists(conn, username, email)) {
                response.sendRedirect("register.jsp?error=Username or email already exists");
                return;
            }

            // Hash password before storing
            String hashedPassword = PasswordHasher.hashPassword(password);

            // Start transaction
            conn.setAutoCommit(false);

            try {
                // Insert into Users table
                int userId = insertUser(conn, username, hashedPassword, email, role, fullName, contact);

                // Insert into specific role table
                if ("student".equals(role)) {
                    insertStudent(conn, userId, request);
                } else if ("hr".equals(role)) {
                    insertHR(conn, userId, request);
                } else if ("placement_admin".equals(role)) {
                    insertAdmin(conn, userId);
                }

                conn.commit();
                response.sendRedirect("login.jsp?success=Registration successful. Please login.");

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Registration failed. Please try again.");
        }
    }

    private boolean userExists(Connection conn, String username, String email) throws SQLException {
        String sql = "SELECT 1 FROM Users WHERE username = ? OR email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            return stmt.executeQuery().next();
        }
    }

    private int insertUser(Connection conn, String username, String password, String email,
                           String role, String fullName, String contact) throws SQLException {
        String sql = "INSERT INTO Users (username, password, email, role, full_name, contact_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, role);
            stmt.setString(5, fullName);
            stmt.setString(6, contact);

            stmt.executeUpdate();

            try (java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to insert user, no ID obtained.");
    }

    private void insertStudent(Connection conn, int userId, HttpServletRequest request) throws SQLException {
        String sql = "INSERT INTO Students (student_id, branch, semester, cgpa, skills) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, request.getParameter("branch"));
            stmt.setInt(3, Integer.parseInt(request.getParameter("semester")));

            String cgpa = request.getParameter("cgpa");
            stmt.setDouble(4, cgpa != null && !cgpa.isEmpty() ? Double.parseDouble(cgpa) : 0.0);

            stmt.setString(5, request.getParameter("skills"));
            stmt.executeUpdate();
        }
    }

    private void insertHR(Connection conn, int userId, HttpServletRequest request) throws SQLException {
        String sql = "INSERT INTO HR (hr_id, company_name, company_description) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, request.getParameter("company"));
            stmt.setString(3, request.getParameter("company-desc"));
            stmt.executeUpdate();
        }
    }

    private void insertAdmin(Connection conn, int userId) throws SQLException {
        String sql = "INSERT INTO Placement_Admins (admin_id) VALUES (?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}