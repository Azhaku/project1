<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Buyer Login</title>
<style>
@import
	url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap')
	;

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Poppins', sans-serif;
}

html, body {
	display: grid;
	height: 100%;
	width: 100%;
	place-items: center;
	background: white;
}

::selection {
	background: grey;
	color: #fff;
}

.wrapper {
	width: 500px;
	overflow: hidden;
	max-width: 500px;
	background: #fff;
	padding: 30px;
	border-radius: 5px;
	box-shadow: 0px 15px 20px rgba(0, 0, 0, 0.1);
	margin-bottom: 50px;
}

.wrapper .title-text {
	display: flex;
	width: 200%;
}

.wrapper .title {
	width: 50%;
	font-size: 25px;
	font-weight: 600;
	text-align: center;
	transition: all 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.wrapper .slide-controls {
	position: relative;
	display: flex;
	height: 50px;
	width: 100%;
	overflow: hidden;
	margin: 30px 0 10px 0;
	justify-content: space-between;
	border: 1px solid lightgrey;
	border-radius: 15px;
}

.slide-controls .slide {
	height: 100%;
	width: 100%;
	color: #fff;
	font-size: 10px;
	font-weight: 500;
	text-align: center;
	line-height: 48px;
	cursor: pointer;
	z-index: 1;
	transition: all 0.6s ease;
}

.slide-controls label.signup {
	color: #000;
}

.slide-controls .slider-tab {
	position: absolute;
	height: 100%;
	width: 50%;
	left: 0;
	z-index: 0;
	border-radius: 15px;
	background:grey;
	transition: all 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

input[type="radio"] {
	display: none;
}

#signup:checked ~ .slider-tab {
	left: 50%;
}

#signup:checked ~ label.signup {
	color: #fff;
	cursor: default;
	user-select: none;
}

#signup:checked ~ label.login {
	color: #000;
}

#login:checked ~ label.signup {
	color: #000;
}

#login:checked ~ label.login {
	cursor: default;
	user-select: none;
}

.wrapper .form-container {
	width: 100%;
	overflow: hidden;
}

.form-container .form-inner {
	display: flex;
	width: 200%;
}

.form-container .form-inner form {
	padding: 30px;
	width: 50%;
	transition: all 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.form-inner form .field {
	padding: 10px
	background-color:red;
	height: 50px;
	width: 100%;
	margin-top: 20px;
}

.form-inner form .field input {
	height: 100%;
	width: 100%;
	outline: none;
	padding-left: 15px;
	border-radius: 15px;
	border: 1px solid lightgrey;
	border-bottom-width: 2px;
	font-size: 10px;
	transition: all 0.3s ease;
}

.form-inner form .field input:focus {
	border-color: #1a75ff;
	/* box-shadow: inset 0 0 3px #fb6aae; */
}

.form-inner form .field input::placeholder {
	color: #999;
	transition: all 0.3s ease;
}

form .field input:focus::placeholder {
	color: #1a75ff;
}

.form-inner form .pass-link {
	margin-top: 5px;
}

.form-inner form .signup-link {
	text-align: center;
	margin-top: 30px;
}

.form-inner form .pass-link a, .form-inner form .signup-link a {
	color: #1a75ff;
	text-decoration: none;
}

.form-inner form .pass-link a:hover, .form-inner form .signup-link a:hover
	{
	text-decoration: underline;
}
.otp-section{
background:red;
}
form .btn {
	height: 50px;
	width: 100%;
	border-radius: 15px;
	position: relative;
	overflow: hidden;
}

form .btn .btn-layer {
	height: 100%;
	width: 300%;
	position: absolute;
	left: -100%;
	background: white;
	border-radius: 15px;
	transition: all 0.4s ease;;
}

form .btn:hover .btn-layer {
	left: 0;
}

form .btn input[type="submit"] {
	height: 100%;
	width: 100%;
	z-index: 1;
	position: relative;
	background: none;
	border: none;
	color: #fff;
	padding-left: 0;
	border-radius: 3px;
	font-size: 15px;
	font-weight: 500;
	cursor: pointer;
}
</style>
</head>
<body>
<div class="wrapper">
	<div class="title-text">
		<div class="title login">Register</div>
		<div class="title signup">Signup</div>
	</div>
	<div class="form-container">
	
		<div class="form-inner">
					
			    <!-- ***** Regiter form ***** -->
			
			<form action="/seller/register" class="signup" method ="post">
				<div class="field">
			        <input type="text" id="username" name="username" placeholder="Enter your username" required>
			    </div>
			
			    <div class="field">
			        <input type="email" id="email" name="email" placeholder="Enter your email" required>
			    </div>
			
			    <div class="field">
			        <input type="password" id="password" name="password" placeholder="Enter your password" required>
			    </div>
			
			    <div class="field">
			        <input type="tel" id="phoneNumber" name="phoneNumber" placeholder="Enter your phone number" required>
			    </div>
			
			    <div class="field">
			        <input type="text" id="address" name="address" placeholder="Enter your address">
			    </div>
			    <div class="field">
			     <input type="button" id="verifyEmailBtn" value="Send OTP"/>
			    </div>
					
				<!-- OTP input field, hidden initially -->
				<div  class="field" id ="otp-section" style="display: none;">
					<input type="text" id="otpBox" placeholder="Enter OTP" name="otp" maxlength="6" required />
				</div>
				<div class="field" id ="otp-section1" style="display: none;">
					<input type="button" id="submitOtpBtn" value="Submit OTP" />
				</div>
		    	
				<div class="field btn">
					<div class="btn-layer"></div>
					<input type="submit" id="registerSubmitBtn" value="Signup" disabled>
				</div>
				<div class="signup-link">
					 Already a member? <a href="/seller/login">Sign in</a>
				</div>
			</form>
			
			
		</div>
	</div>
</div>
</body>
<script type="text/javascript">


    document.getElementById("verifyEmailBtn").addEventListener("click", function() {
        console.log("working");
        const email = document.getElementById("email").value;
		console.log(email);
        // Send request to trigger OTP email
        fetch('/seller/send-otp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email: email }) // Send email as JSON
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("OTP sent successfully to " + email);
                document.getElementById("otp-section").style.display = "block"; // Show OTP input field
                document.getElementById("otp-section1").style.display = "block"; // Show OTP input field
                
            } else {
                alert("Failed to send OTP. Please try again.");
            }
        })
        .catch(error => console.error('Error:', error));
    });

    document.getElementById("submitOtpBtn").addEventListener("click", function() {
        const otp = document.getElementById("otpBox").value;
        const email = document.getElementById("email").value;
        console.log(email);
        
        /* const encodedEmail = encodeURIComponent(email);
        const encodedOtp = encodeURIComponent(otp); */
    	
        console.log(`Encoded Email: ${email}, Encoded OTP: ${otp}`);

        // Perform the fetch request
        fetch('/seller/verify-otp?email='+email+'&otp='+otp, {
		            method: 'POST',
		            headers: {
		                'Content-Type': 'application/json'
		            }
		        })
		 
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("OTP verified successfully!");
                const registerButton = document.getElementById("registerSubmitBtn");
                registerButton.disabled = false;
                registerButton.style.backgroundColor = "#4CAF50";  // Green
   				
                // document.getElementById("registerSubmitBtn").disabled = false; // Enable the main register button
                document.getElementById("otp-section").style.display = "none"; // Optionally hide the OTP section after verification
            } else {
                alert("Invalid OTP. Please try again.");
            }
        })
        .catch(error => console.error('Error:', error));
    });
</script>
    
