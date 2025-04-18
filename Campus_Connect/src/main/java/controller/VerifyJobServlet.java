package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import util.DBUtil;

@WebServlet("/VerifyJobServlet")
public class VerifyJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if user is logged in and is admin
        if (user == null || !user.getRole().equals("placement_admin")) {
            response.sendRedirect("login.jsp");
            return;
        }

        int jobId = Integer.parseInt(request.getParameter("jobId"));
        String action = request.getParameter("action");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE Jobs SET is_verified = ?, verified_by = ?, verified_date = ? " +
                    "WHERE job_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setBoolean(1, "approve".equals(action));
                stmt.setInt(2, user.getUserId());
                stmt.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
                stmt.setInt(4, jobId);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    String message = "Job " + ("approve".equals(action) ? "approved" : "rejected") + " successfully";
                    response.sendRedirect("AdminDashboardServlet?success=" + message);
                } else {
                    response.sendRedirect("AdminDashboardServlet?error=operation_failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminDashboardServlet?error=database_error");
        }
    }
}