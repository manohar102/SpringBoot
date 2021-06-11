<%@ page language= "java" contentType="text/html; charset =UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<meta = charset="UTF-8">
	<title>
		Welcome
	</title>
	<body>
		<h1> Welcome Manohar</h1>
		<form action=addEmp method="POST">
			Enter Employee ID : <input type="text" name="id"/>
			Enter Employee Name : <input type="text" name="name"/>
			<input type="submit">
		</form>
		<hr>
		<form action=getEmployeeById method="GET">
			Enter Employee ID : <input type="text" name="eid"/>
			<input type="submit">
		</form>
		<hr>
		<form action=getEmployeeByName method="GET">
			Enter Employee Name : <input type="text" name="ename"/>
			<input type="submit">
		</form>
		
	</body>
</html>