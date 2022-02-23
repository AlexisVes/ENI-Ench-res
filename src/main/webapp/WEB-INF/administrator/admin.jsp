<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
	<h1>Administration - Gestion des utilisateurs</h1>
	
	<c:forEach var="pseudo" items="${requestScope.allUsers }">
		<p>POPOPO<p>
	</c:forEach>
	
</body>
</html>