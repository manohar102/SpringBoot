
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
		height: 300px;
		margin-top:220px;
	}
	#sub{
		background-color:#331b59;
		color:white;
		width:100px;
		border-radius:20px;
	}
	#error{
		color:red;
	}
	#result{ color : green;}
</style>
<body>
	<center>
		<div>
			<p id="error">${error}</p>	
			<p id="result">${result}</p>
			<h2>Login</h2>
			<form action="/" Method=POST>
				<input type="text" name="username" placeholder="Username"/><br>
				<input type="password" name="password" placeholder="Password"/><br>
				<input type="submit" id="sub" value="Login"/>
			</form>
			<a href="register"> Don't have account? </a>
		</div>
	</center>
	
</body>
</html>