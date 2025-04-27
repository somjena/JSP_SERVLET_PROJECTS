<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
User user = (User) session.getAttribute("user");
String role = (user != null) ? user.getRole() : "";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus Connect</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Header specific styles */
        header {
            background-color: #2c3e50;
            color: white;
            padding: 15px 0;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            position: sticky;
            top: 0;
            z-index: 100;
        }

        .header-container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            font-size: 24px;
            font-weight: bold;
            color: white;
            text-decoration: none;
        }

        .logo:hover {
            color: #ecf0f1;
        }

        nav ul {
            display: flex;
            list-style: none;
            margin: 0;
            padding: 0;
        }

        nav ul li {
            margin-left: 20px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: 500;
            padding: 5px 10px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        nav ul li a:hover {
            background-color: rgba(255,255,255,0.1);
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-info span {
            margin-right: 15px;
        }

        .logout-btn {
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        .logout-btn:hover {
            background-color: #c0392b;
        }

        /* Responsive styles */
        @media (max-width: 768px) {
            .header-container {
                flex-direction: column;
                text-align: center;
            }

            nav ul {
                margin-top: 15px;
                flex-wrap: wrap;
                justify-content: center;
            }

            nav ul li {
                margin: 5px 10px;
            }

            .user-info {
                margin-top: 15px;
            }
        }
    </style>
</head>
<body>
    <header>
        <div class="header-container">
            <a href="index.jsp" class="logo">Campus Connect</a>

            <nav>
                <ul>
                    <% if (user == null) { %>
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="login.jsp">Login</a></li>
                        <li><a href="register.jsp">Register</a></li>
                    <% } else { %>
                        <li><a href="<%= role.equals("student") ? "student_dashboard.jsp" :
                                      role.equals("hr") ? "hr_dashboard.jsp" :
                                      "admin_dashboard.jsp" %>">Dashboard</a></li>

                        <% if (role.equals("student")) { %>
                            <li><a href="ViewJobsServlet">Browse Jobs</a></li>
                            <li><a href="my_applications.jsp">My Applications</a></li>
                        <% } else if (role.equals("hr")) { %>
                            <li><a href="post_job.jsp">Post Job</a></li>
                            <li><a href="ViewApplicationsServlet">View Applications</a></li>
                        <% } else if (role.equals("placement_admin")) { %>
                            <li><a href="VerifyJobsServlet">Verify Jobs</a></li>
                            <li><a href="manage_users.jsp">Manage Users</a></li>
                        <% } %>

                        <li><a href="profile.jsp">Profile</a></li>
                    <% } %>
                </ul>
            </nav>

            <% if (user != null) { %>
                <div class="user-info">
                    <span>Welcome, <%= user.getFullName() %></span>
                    <form action="logout" method="get">
                        <button type="submit" class="logout-btn">Logout</button>
                    </form>
                </div>
            <% } %>
        </div>
    </header>

    <main>