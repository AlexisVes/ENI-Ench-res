<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.connect == null || sessionScope.connect eq 'Mauvais pseudo ou mot de passe'}">
		
			<form action="${pageContext.request.contextPath}/login" method="Post" >
		
				<input type="text" name="pseudo" placeholder="Votre pseudo">
				<input type="password" name="password">
				<input type="submit" value="Valider">
		
			</form>
		
		</c:if>
		
		
		<p>
			${sessionScope.connect}
		</p>
		
		<a href="${pageContext.request.contextPath}/register"> S'inscrire</a>
		
	
		
	</body>
	
</html>