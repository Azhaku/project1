<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seller Profile</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4; /* Light background for contrast */
            color: #333; /* Dark text color */
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #444; /* Slightly lighter black */
            animation: fadeIn 1s; /* Fade-in animation */
        }

        form {
            background-color: #222; /* Mild black background */
            color: #fff; /* White text */
            border-radius: 8px;
            padding: 20px;
            max-width: 400px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            animation: slideIn 0.5s; /* Slide-in animation */
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: none;
            border-radius: 4px;
            box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.3);
        }

        input[type="submit"] {
            background-color: #4CAF50; /* Green button */
            color: white;
            border: none;
            border-radius: 4px;
            padding: 10px;
            cursor: pointer;
            transition: background-color 0.3s; /* Transition effect */
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #45a049; /* Darker green on hover */
        }

        p {
            text-align: center;
            margin-top: 20px;
        }

        a {
            color: #333; /* Green link color */
            text-decoration: none;
            transition: color 0.3s; /* Transition effect */
        }

        a:hover {
            color: #333; /* Darker green on hover */
        }

        /* Success message style */
        .success-message {
            background-color: #dff0d8;
            color: #3c763d;
            border: 1px solid #d6e9c6;
            padding: 10px;
            text-align: center;
            border-radius: 4px;
            margin-bottom: 20px;
            animation: fadeIn 1s;
        }

        /* Error message style */
        .error-message {
            background-color: #f2dede;
            color: #a94442;
            border: 1px solid #ebccd1;
            padding: 10px;
            text-align: center;
            border-radius: 4px;
            margin-bottom: 20px;
            animation: fadeIn 1s;
        }

        /* Animation Keyframes */
        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        @keyframes slideIn {
            from {
                transform: translateY(-20px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
    </style>
</head>
<body>
    <h2>Sellers Profile</h2>
    
    <!-- Display success message if present -->
    <c:if test="${not empty successMessage}">
        <div class="success-message">${successMessage}</div>
    </c:if>

    <!-- Display error message if present -->
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/sellers/updateProfile" method="post">
        <label for="email">Email:</label>
        <input type="text" name="email" value="${seller.email}" required/>

        <label for="phoneNumber">Phone Number:</label>
        <input type="text" name="phoneNumber" value="${seller.phoneNumber}" required/>

        <label for="address">Address:</label>
        <input type="text" name="address" value="${seller.address}" required/>

        <label for="password">Password:</label>
        <input type="password" name="password"/>

        <input type="submit" value="Update Profile"/>
    </form>

    <p><a href="${pageContext.request.contextPath}/sellers/dashboard">Back to Dashboard</a></p>
</body>
</html>
