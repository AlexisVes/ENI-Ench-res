<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	
		<form action="${pageContext.request.contextPath}/login" method="Post" >
		
			<input type="text" name="pseudo" placeholder="Votre pseudo">
			<input type="password" name="password">
			<input type="submit" value="Valider">
		
		</form>
		
		<p> ${requestScope.message} </p>
	
	</body>
</html>