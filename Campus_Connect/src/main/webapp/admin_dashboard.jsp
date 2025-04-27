<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Campus Connect</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        .dashboard-container {
            display: flex;
            min-height: calc(100vh - 120px);
        }
        .sidebar {
            width: 250px;
            background-color: #343a40;
            color: white;
            padding: 20px 0;
        }
        .sidebar-menu {
            list-style: none;
            padding: 0;
        }
        .sidebar-menu li {
            padding: 10px 20px;
        }
        .sidebar-menu li a {
            color: white;
            text-decoration: none;
            display: block;
        }
        .sidebar-menu li a:hover {
            background-color: #495057;
        }
        .sidebar-menu li.active {
            background-color: #007bff;
        }
        .main-content {
            flex: 1;
            padding: 20px;
        }
        .welcome-header {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .stats-cards {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            margin-bottom: 20px;
        }
        .card {
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            padding: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="dashboard-container">
        <!-- Sidebar Navigation -->
        <div class="sidebar">
            <ul class="sidebar-menu">
                <li class="active"><a href="admin_dashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a></li>
                <li><a href="manage_users.jsp"><i class="fas fa-users"></i> Manage Users</a></li>
                <li><a href="manage_jobs.jsp"><i class="fas fa-briefcase"></i> Manage Jobs</a></li>
                <li><a href="manage_applications.jsp"><i class="fas fa-file-alt"></i> Applications</a></li>
                <li><a href="reports.jsp"><i class="fas fa-chart-bar"></i> Reports</a></li>
                <li><a href="settings.jsp"><i class="fas fa-cog"></i> Settings</a></li>
            </ul>
        </div>

        <!-- Main Content Area -->
        <div class="main-content">
            <div class="welcome-header">
                <%
                    User user = (User) session.getAttribute("user");
                    if (user != null) {
                %>
                <h1>Welcome, <%= user.getFullName() != null ? user.getFullName() : user.getUsername() %>!</h1>
                <p>Placement Administrator Dashboard</p>
                <p>Role: <%= user.getRole() %></p>
                <%
                    } else {
                        response.sendRedirect("login.jsp");
                    }
                %>
            </div>

            <div class="stats-cards">
                <div class="card">
                    <h3><i class="fas fa-users"></i> Total Students</h3>
                    <p>1,245</p>
                </div>
                <div class="card">
                    <h3><i class="fas fa-building"></i> Companies</h3>
                    <p>86</p>
                </div>
                <div class="card">
                    <h3><i class="fas fa-briefcase"></i> Active Jobs</h3>
                    <p>124</p>
                </div>
            </div>

            <div class="card">
                <h3>Recent Activities</h3>
                <ul>
                    <li>5 new student registrations today</li>
                    <li>3 new job postings this week</li>
                    <li>12 applications received yesterday</li>
                </ul>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>