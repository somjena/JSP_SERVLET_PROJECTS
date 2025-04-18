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

import model.Job;
import model.Student;
import model.User;
import util.DBUtil;

@WebServlet("/ViewJobsServlet")
public class ViewJobsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("student")) {
            response.sendRedirect("login.jsp");
            return;
        }

        Student student = (Student) session.getAttribute("student");
        String branchFilter = request.getParameter("branch");

        String sql = "SELECT j.*, h.company_name FROM Jobs j " +
                "JOIN HR h ON j.hr_id = h.hr_id " +
                "WHERE j.is_verified = TRUE " +
                "AND j.deadline >= CURDATE() ";

        // If branch filter is applied
        if (branchFilter != null && !branchFilter.isEmpty()) {
            sql += "AND j.eligible_branches LIKE ? ";
        } else {
            // Show only jobs matching student's branch
            sql += "AND j.eligible_branches LIKE ? ";
            branchFilter = "%" + student.getBranch() + "%";
        }

        sql += "AND j.min_cgpa <= ? " +
                "ORDER BY j.posted_date DESC";

        List<Job> jobs = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + branchFilter + "%");
            stmt.setDouble(2, student.getCgpa());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setTitle(rs.getString("title"));
                job.setLocation(rs.getString("location"));
                job.setDeadline(rs.getDate("deadline"));
                // Set other properties

                jobs.add(job);
            }

            request.setAttribute("jobs", jobs);
            request.getRequestDispatcher("student_dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("student_dashboard.jsp?error=database_error");
        }
    }
}