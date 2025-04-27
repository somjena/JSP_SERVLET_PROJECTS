<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Placement Connect - Bridging Students and Companies</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        :root {
            --primary: #4361ee;
            --secondary: #3f37c9;
            --accent: #4895ef;
            --light: #f8f9fa;
            --dark: #212529;
            --success: #4cc9f0;
            --danger: #f72585;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background-color: var(--light);
            color: var(--dark);
            line-height: 1.6;
        }

        /* Header Styles */
        header {
            background-color: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            position: fixed;
            width: 100%;
            z-index: 1000;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 5%;
            max-width: 1400px;
            margin: 0 auto;
        }

        .logo {
            display: flex;
            align-items: center;
            text-decoration: none;
        }

        .logo-icon {
            color: var(--primary);
            font-size: 2rem;
            margin-right: 0.5rem;
        }

        .logo-text {
            font-size: 1.5rem;
            font-weight: 700;
            color: var(--dark);
        }

        .logo-text span {
            color: var(--primary);
        }

        .nav-links {
            display: flex;
            list-style: none;
        }

        .nav-links li {
            margin-left: 2rem;
        }

        .nav-links a {
            text-decoration: none;
            color: var(--dark);
            font-weight: 500;
            transition: color 0.3s;
        }

        .nav-links a:hover {
            color: var(--primary);
        }

        .auth-buttons .btn {
            margin-left: 1rem;
            padding: 0.5rem 1.5rem;
            border-radius: 50px;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.3s;
        }

        .btn-login {
            color: var(--primary);
            border: 2px solid var(--primary);
            background: transparent;
        }

        .btn-login:hover {
            background-color: var(--primary);
            color: white;
        }

        .btn-register {
            background-color: var(--primary);
            color: white;
            border: 2px solid var(--primary);
        }

        .btn-register:hover {
            background-color: var(--secondary);
            border-color: var(--secondary);
        }

        /* Hero Section */
        .hero {
            padding: 10rem 5% 5rem;
            background: linear-gradient(135deg, rgba(67, 97, 238, 0.1) 0%, rgba(72, 149, 239, 0.1) 100%);
            text-align: center;
        }

        .hero-content {
            max-width: 800px;
            margin: 0 auto;
        }

        .hero h1 {
            font-size: 3.5rem;
            margin-bottom: 1.5rem;
            color: var(--dark);
        }

        .hero h1 span {
            color: var(--primary);
        }

        .hero p {
            font-size: 1.2rem;
            margin-bottom: 2rem;
            color: #555;
        }

        .hero-buttons {
            display: flex;
            justify-content: center;
            gap: 1rem;
            margin-top: 2rem;
        }

        .hero-buttons .btn {
            padding: 0.8rem 2rem;
            font-size: 1.1rem;
        }

        .hero-image {
            margin-top: 3rem;
            max-width: 100%;
            border-radius: 10px;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
        }

        /* Features Section */
        .features {
            padding: 5rem 5%;
            background-color: white;
        }

        .section-title {
            text-align: center;
            margin-bottom: 3rem;
        }

        .section-title h2 {
            font-size: 2.5rem;
            color: var(--dark);
        }

        .section-title p {
            color: #666;
            max-width: 600px;
            margin: 0 auto;
        }

        .features-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .feature-card {
            background-color: white;
            border-radius: 10px;
            padding: 2rem;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
            transition: transform 0.3s, box-shadow 0.3s;
            text-align: center;
        }

        .feature-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
        }

        .feature-icon {
            font-size: 2.5rem;
            color: var(--primary);
            margin-bottom: 1.5rem;
        }

        .feature-card h3 {
            font-size: 1.5rem;
            margin-bottom: 1rem;
            color: var(--dark);
        }

        /* Stats Section */
        .stats {
            padding: 5rem 5%;
            background: linear-gradient(135deg, var(--primary) 0%, var(--accent) 100%);
            color: white;
            text-align: center;
        }

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 2rem;
            max-width: 1000px;
            margin: 0 auto;
        }

        .stat-item h3 {
            font-size: 3rem;
            margin-bottom: 0.5rem;
        }

        .stat-item p {
            font-size: 1.1rem;
            opacity: 0.9;
        }

        /* How It Works Section */
        .how-it-works {
            padding: 5rem 5%;
            background-color: white;
        }

        .steps {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .step {
            flex: 1;
            min-width: 250px;
            text-align: center;
            position: relative;
        }

        .step-number {
            width: 50px;
            height: 50px;
            background-color: var(--primary);
            color: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5rem;
            font-weight: bold;
            margin: 0 auto 1.5rem;
        }

        .step h3 {
            margin-bottom: 1rem;
            color: var(--dark);
        }

        /* Testimonials */
        .testimonials {
            padding: 5rem 5%;
            background-color: var(--light);
        }

        .testimonial-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .testimonial-card {
            background-color: white;
            border-radius: 10px;
            padding: 2rem;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
        }

        .testimonial-text {
            font-style: italic;
            margin-bottom: 1.5rem;
            color: #555;
        }

        .testimonial-author {
            display: flex;
            align-items: center;
        }

        .author-avatar {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 1rem;
        }

        .author-info h4 {
            color: var(--dark);
            margin-bottom: 0.2rem;
        }

        .author-info p {
            color: #777;
            font-size: 0.9rem;
        }

        /* CTA Section */
        .cta {
            padding: 5rem 5%;
            background: linear-gradient(135deg, var(--primary) 0%, var(--accent) 100%);
            color: white;
            text-align: center;
        }

        .cta h2 {
            font-size: 2.5rem;
            margin-bottom: 1.5rem;
        }

        .cta p {
            max-width: 600px;
            margin: 0 auto 2rem;
            opacity: 0.9;
        }

        /* Footer */
        footer {
            background-color: var(--dark);
            color: white;
            padding: 3rem 5%;
        }

        .footer-content {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .footer-column h3 {
            font-size: 1.2rem;
            margin-bottom: 1.5rem;
            position: relative;
            padding-bottom: 0.5rem;
        }

        .footer-column h3::after {
            content: '';
            position: absolute;
            left: 0;
            bottom: 0;
            width: 50px;
            height: 2px;
            background-color: var(--primary);
        }

        .footer-column ul {
            list-style: none;
        }

        .footer-column ul li {
            margin-bottom: 0.8rem;
        }

        .footer-column ul li a {
            color: #aaa;
            text-decoration: none;
            transition: color 0.3s;
        }

        .footer-column ul li a:hover {
            color: white;
        }

        .social-links {
            display: flex;
            gap: 1rem;
            margin-top: 1rem;
        }

        .social-links a {
            color: white;
            background-color: rgba(255, 255, 255, 0.1);
            width: 40px;
            height: 40px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: background-color 0.3s;
        }

        .social-links a:hover {
            background-color: var(--primary);
        }

        .copyright {
            text-align: center;
            padding-top: 2rem;
            margin-top: 2rem;
            border-top: 1px solid rgba(255, 255, 255, 0.1);
            color: #aaa;
            font-size: 0.9rem;
        }

        /* Responsive Styles */
        @media (max-width: 768px) {
            .navbar {
                flex-direction: column;
                padding: 1rem;
            }

            .nav-links {
                margin: 1rem 0;
            }

            .auth-buttons {
                margin-top: 1rem;
            }

            .hero h1 {
                font-size: 2.5rem;
            }

            .hero-buttons {
                flex-direction: column;
                align-items: center;
            }

            .hero-buttons .btn {
                width: 100%;
                max-width: 300px;
            }
        }
    </style>
</head>
<body>
    <!-- Header -->
    <header>
        <nav class="navbar">
            <a href="index.jsp" class="logo">
                <i class="fas fa-handshake logo-icon"></i>
                <span class="logo-text">Placement<span>Connect</span></span>
            </a>

            <ul class="nav-links">
                <li><a href="#features">Features</a></li>
                <li><a href="#how-it-works">How It Works</a></li>
                <li><a href="#testimonials">Testimonials</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>

            <div class="auth-buttons">
                <a href="login.jsp" class="btn btn-login">Login</a>
                <a href="register.jsp" class="btn btn-register">Register</a>
            </div>
        </nav>
    </header>

    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h1>Bridge the Gap Between <span>Students and Companies</span></h1>
            <p>Placement Connect is the premier platform connecting talented students with top companies for internships and full-time positions. Start your journey today!</p>

            <div class="hero-buttons">
                <a href="register.jsp?role=student" class="btn btn-register">I'm a Student</a>
                <a href="register.jsp?role=hr" class="btn btn-login">I'm a Recruiter</a>
            </div>

            <img src="https://images.unsplash.com/photo-1522202176988-66273c2fd55f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80" alt="Students and professionals networking" class="hero-image">
        </div>
    </section>

    <!-- Features Section -->
    <section class="features" id="features">
        <div class="section-title">
            <h2>Why Choose Placement Connect?</h2>
            <p>Our platform offers unique benefits for both students and recruiters</p>
        </div>

        <div class="features-grid">
            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-bullseye"></i>
                </div>
                <h3>Targeted Matching</h3>
                <p>Our advanced algorithm matches students with companies based on skills, preferences, and requirements.</p>
            </div>

            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-chart-line"></i>
                </div>
                <h3>Career Analytics</h3>
                <p>Get detailed insights into your placement statistics and improve your chances of getting hired.</p>
            </div>

            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-comments"></i>
                </div>
                <h3>Direct Communication</h3>
                <p>Students and recruiters can communicate directly through our secure messaging platform.</p>
            </div>
        </div>
    </section>

    <!-- Stats Section -->
    <section class="stats">
        <div class="stats-grid">
            <div class="stat-item">
                <h3>10,000+</h3>
                <p>Students Registered</p>
            </div>

            <div class="stat-item">
                <h3>500+</h3>
                <p>Partner Companies</p>
            </div>

            <div class="stat-item">
                <h3>85%</h3>
                <p>Placement Rate</p>
            </div>

            <div class="stat-item">
                <h3>50+</h3>
                <p>Colleges Onboarded</p>
            </div>
        </div>
    </section>

    <!-- How It Works Section -->
    <section class="how-it-works" id="how-it-works">
        <div class="section-title">
            <h2>How It Works</h2>
            <p>Getting started with Placement Connect is simple and straightforward</p>
        </div>

        <div class="steps">
            <div class="step">
                <div class="step-number">1</div>
                <h3>Create Your Profile</h3>
                <p>Students create detailed profiles with skills, education, and preferences. Recruiters set up company profiles.</p>
            </div>

            <div class="step">
                <div class="step-number">2</div>
                <h3>Find Matches</h3>
                <p>Our system suggests potential matches based on your profile and requirements.</p>
            </div>

            <div class="step">
                <div class="step-number">3</div>
                <h3>Connect & Succeed</h3>
                <p>Start conversations, schedule interviews, and secure your dream job or ideal candidate.</p>
            </div>
        </div>
    </section>

    <!-- Testimonials Section -->
    <section class="testimonials" id="testimonials">
        <div class="section-title">
            <h2>Success Stories</h2>
            <p>Hear from students and recruiters who found success with Placement Connect</p>
        </div>

        <div class="testimonial-grid">
            <div class="testimonial-card">
                <p class="testimonial-text">"Placement Connect helped me land my dream job at a top tech company right after graduation. The platform made it so easy to connect with recruiters."</p>
                <div class="testimonial-author">
                    <img src="https://randomuser.me/api/portraits/women/32.jpg" alt="Sarah Johnson" class="author-avatar">
                    <div class="author-info">
                        <h4>Sarah Johnson</h4>
                        <p>Software Engineer at TechCorp</p>
                    </div>
                </div>
            </div>

            <div class="testimonial-card">
                <p class="testimonial-text">"As a recruiter, I've found Placement Connect to be invaluable for sourcing top talent. The quality of candidates is consistently high."</p>
                <div class="testimonial-author">
                    <img src="https://randomuser.me/api/portraits/men/45.jpg" alt="Michael Chen" class="author-avatar">
                    <div class="author-info">
                        <h4>Michael Chen</h4>
                        <p>HR Manager at Innovate Inc.</p>
                    </div>
                </div>
            </div>

            <div class="testimonial-card">
                <p class="testimonial-text">"The analytics tools helped me understand what skills I needed to improve, and within months I had multiple job offers!"</p>
                <div class="testimonial-author">
                    <img src="https://randomuser.me/api/portraits/women/68.jpg" alt="Priya Patel" class="author-avatar">
                    <div class="author-info">
                        <h4>Priya Patel</h4>
                        <p>Recent Graduate</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- CTA Section -->
    <section class="cta">
        <h2>Ready to Transform Your Career?</h2>
        <p>Join thousands of students and companies who are already benefiting from Placement Connect</p>
        <a href="register.jsp" class="btn btn-register">Get Started Now</a>
    </section>

    <!-- Footer -->
    <footer id="contact">
        <div class="footer-content">
            <div class="footer-column">
                <h3>About Us</h3>
                <p>Placement Connect is dedicated to bridging the gap between academic institutions and industry, creating opportunities for students and quality talent for companies.</p>
                <div class="social-links">
                    <a href="#"><i class="fab fa-facebook-f"></i></a>
                    <a href="#"><i class="fab fa-twitter"></i></a>
                    <a href="#"><i class="fab fa-linkedin-in"></i></a>
                    <a href="#"><i class="fab fa-instagram"></i></a>
                </div>
            </div>

            <div class="footer-column">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="#features">Features</a></li>
                    <li><a href="#how-it-works">How It Works</a></li>
                    <li><a href="#testimonials">Testimonials</a></li>
                    <li><a href="login.jsp">Login</a></li>
                    <li><a href="register.jsp">Register</a></li>
                </ul>
            </div>

            <div class="footer-column">
                <h3>Support</h3>
                <ul>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">Help Center</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                    <li><a href="#">Terms of Service</a></li>
                </ul>
            </div>

            <div class="footer-column">
                <h3>Contact Us</h3>
                <ul>
                    <li><i class="fas fa-map-marker-alt"></i> 123 Campus Drive, EduCity</li>
                    <li><i class="fas fa-phone"></i> +1 (555) 123-4567</li>
                    <li><i class="fas fa-envelope"></i> info@placementconnect.com</li>
                </ul>
            </div>
        </div>

        <div class="copyright">
            <p>&copy; 2023 Placement Connect. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>