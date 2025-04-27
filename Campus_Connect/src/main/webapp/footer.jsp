    </main>

    <footer style="background-color: #34495e; color: white; padding: 20px 0; margin-top: 30px;">
        <div style="width: 90%; max-width: 1200px; margin: 0 auto; text-align: center;">
            <p>&copy; 2023 Campus Connect. All rights reserved.</p>
            <div style="margin-top: 10px;">
                <a href="about.jsp" style="color: #ecf0f1; margin: 0 10px;">About Us</a>
                <a href="contact.jsp" style="color: #ecf0f1; margin: 0 10px;">Contact</a>
                <a href="privacy.jsp" style="color: #ecf0f1; margin: 0 10px;">Privacy Policy</a>
                <a href="terms.jsp" style="color: #ecf0f1; margin: 0 10px;">Terms of Service</a>
            </div>
        </div>
    </footer>

    <script>
        // Highlight current page in navigation
        document.addEventListener('DOMContentLoaded', function() {
            const currentPage = window.location.pathname.split('/').pop();
            const navLinks = document.querySelectorAll('nav ul li a');

            navLinks.forEach(link => {
                if (link.getAttribute('href') === currentPage) {
                    link.style.backgroundColor = 'rgba(255,255,255,0.2)';
                    link.style.fontWeight = 'bold';
                }
            });
        });
    </script>
</body>
</html>