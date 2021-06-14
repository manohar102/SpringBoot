<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
</head>
<style>
	input{
		padding:10px;
		margin:10px;
		width:300px;
		border-radius:10px;
	}
	body{
		background-color:#f2f2f2;
	}
	div{
		background-color:white;
		padding:10px;
		border-radius:10px;
		width: 500px;
		margin-top:180px;
	}
	#sub{
		background-color:#331b59;
		color:white;
		width:100px;
		border-radius:20px;
	}
	p{
		color:red;
	}
</style>
<body>
	<center>
		<div>
			<p>${error}</p>
			<h2>Register</h2>
			<form action="register" method=POST>
				<input type="text" name="fullname" placeholder="Full Name"/><br>
				<input type="text" name="username" placeholder="Username"/><br>
				<input type="password" name="password" placeholder="Password"/><br>
				<input type="password" name="cpassword" placeholder="Confirm Password"/><br>
				<input type="submit" id="sub" value="Register"/>
			</form>
			<a href="/"> Already have account? </a>
		</div>
	</center>
	
</body>
</html>