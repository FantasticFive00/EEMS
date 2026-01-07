<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>EEMS - Login</title>
    <link rel="stylesheet" href="CSS/Login.css">
</head>

<body>

    <div class="login-container">

        <!-- LEFT PANEL -->
        <div class="login-left">
            <h1>Login</h1>
            <p class="subtitle">Login to your account.</p>
            <%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p style="color:red;"><%= error %></p>
<%
    }
%>
            


            <!-- LOGIN FORM -->
            <form action="${pageContext.request.contextPath}/login" method="post">

                <label>Email Address</label>
                <input type="email" name="email" required>

                <label>Password</label>
                <input type="password" name="password" required>

                <div class="options">
                    <label>
                        <input type="checkbox" name="remember">
                        Remember me
                    </label>

                    <a href="forgotPassword.jsp">Reset Password?</a>
                </div>

                <button type="submit" name="role" value="employee" class="btn">
                    Sign In As Employee
                </button>

                <button type="submit" name="role" value="manager" class="btn">
                    Sign In As Manager
                </button>

                <button type="submit" name="role" value="admin" class="btn">
                    Sign In As Admin
                </button>

            </form>
        </div>

        <!-- RIGHT PANEL -->
        <div class="login-right">
            <div class="overlay">
                <p>
                    Kotak Malaysia (KOM) Sdn Bhd was incorporated in 1974 and
                    acquired by MUDA Holding Berhad since 2001. We have more
                    than 40 years of experience in corrugated carton
                    manufacturing, especially for electronic, furniture,
                    glove, plastic, food and beverage industries.
                </p>
            </div>
        </div>
    </div>

</body>

</html>