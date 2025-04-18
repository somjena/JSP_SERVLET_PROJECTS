package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import util.DBUtil;

@WebServlet("/PostJobServlet")
public class PostJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("hr")) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get form parameters
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String requirements = request.getParameter("requirements");
        String location = request.getParameter("location");
        String salary = request.getParameter("salary");
        String deadline = request.getParameter("deadline");
        String[] eligibleBranches = request.getParameterValues("eligibleBranches");
        double minCgpa = Double.parseDouble(request.getParameter("minCgpa"));

        // Convert branches array to comma-separated string
        String branchesStr = String.join(",", eligibleBranches);

        String sql = "INSERT INTO Jobs (hr_id, title, description, requirements, " +
                "location, salary, deadline, eligible_branches, min_cgpa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getUserId());
            stmt.setString(2, title);
            stmt.setString(3, description);
            stmt.setString(4, requirements);
            stmt.setString(5, location);
            stmt.setString(6, salary);
            stmt.setString(7, deadline);
            stmt.setString(8, branchesStr);
            stmt.setDouble(9, minCgpa);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("hr_dashboard.jsp?success=job_posted");
            } else {
                response.sendRedirect("post_job.jsp?error=posting_failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("post_job.jsp?error=database_error");
        }
    }
}