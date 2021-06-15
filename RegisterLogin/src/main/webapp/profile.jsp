<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>To do List</title>
</head>
<body>
	Welcome ${user} | <a href="/logout" >Logout</a>
	
	<div>
		<form action="addNote" method="post">
			<textarea name="note" cols="40" rows="2"></textarea>
			<br> <input type="submit" value="Add Note" />
		</form>
	</div>
	<div>
		<form action="invalidate/session" method="post">
			<input type="submit" value="Destroy Session" />
		</form>
	</div>
	<div>
		<h2>Notes</h2><br>
		${notesSession}
	</div>
	
	
</body>
</html>