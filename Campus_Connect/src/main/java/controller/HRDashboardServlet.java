package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HR;
import model.Job;
import model.User;
import util.DBUtil;

@WebServlet("/HRDashboardServlet")
public class HRDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if user is logged in and is HR
        if (user == null || !user.getRole().equals("hr")) {
            response.sendRedirect("login.jsp");
            return;
        }

        HR hr = (HR) session.getAttribute("hr");
        if (hr == null) {
            // If HR details not in session, load them
            try {
                hr = loadHRDetails(user.getUserId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("hr", hr);
        }

        try (Connection conn = DBUtil.getConnection()) {
            // Get all jobs posted by this HR
            List<Job> jobs = getJobsByHr(conn, user.getUserId());
            request.setAttribute("jobs", jobs);

            // Get statistics
            int totalJobs = getTotalJobsCount(conn, user.getUserId());
            int activeJobs = getActiveJobsCount(conn, user.getUserId());
            int totalApplications = getTotalApplicationsCount(conn, user.getUserId());

            request.setAttribute("totalJobs", totalJobs);
            request.setAttribute("activeJobs", activeJobs);
            request.setAttribute("totalApplications", totalApplications);

            request.getRequestDispatcher("hr_dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private HR loadHRDetails(int userId) throws SQLException {
        String sql = "SELECT * FROM HR WHERE hr_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HR hr = new HR();
                hr.setHrId(rs.getInt("hr_id"));
                hr.setCompanyName(rs.getString("company_name"));
                hr.setCompanyDescription(rs.getString("company_description"));
                hr.setCompanyLogoPath(rs.getString("company_logo_path"));
                return hr;
            }
        }
        return null;
    }

    private List<Job> getJobsByHr(Connection conn, int hrId) throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM Jobs WHERE hr_id = ? ORDER BY posted_date DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hrId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setHrId(rs.getInt("hr_id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setRequirements(rs.getString("requirements"));
                job.setLocation(rs.getString("location"));
                job.setSalary(rs.getString("salary"));
                job.setPostedDate(rs.getDate("posted_date"));
                job.setDeadline(rs.getDate("deadline"));
                job.setVerified(rs.getBoolean("is_verified"));
                job.setVerifiedBy(rs.getInt("verified_by"));
                job.setVerifiedDate(rs.getDate("verified_date"));
                job.setEligibleBranches(rs.getString("eligible_branches"));
                job.setMinCgpa(rs.getDouble("min_cgpa"));

                jobs.add(job);
            }
        }
        return jobs;
    }

    private int getTotalJobsCount(Connection conn, int hrId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Jobs WHERE hr_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hrId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private int getActiveJobsCount(Connection conn, int hrId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Jobs WHERE hr_id = ? AND is_verified = TRUE AND deadline >= CURDATE()";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hrId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private int getTotalApplicationsCount(Connection conn, int hrId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Applications a JOIN Jobs j ON a.job_id = j.job_id " +
                "WHERE j.hr_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hrId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
}