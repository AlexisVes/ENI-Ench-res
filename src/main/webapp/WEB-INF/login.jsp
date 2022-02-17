<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	</head>
	<body>
	
	<a href="${pageContext.request.contextPath}/home" ><img src="./img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
	
		<c:if test="${sessionScope.connect == null || sessionScope.connect eq 'Mauvais pseudo ou mot de passe'}">
		
			<form action="${pageContext.request.contextPath}/login" method="Post" >
		
				<label for="pseudo">Pseudo :</label>
				<input type="text" name="pseudo" placeholder="Votre pseudo">
				<label for="password">Mot de passe :</label>
				<input type="password" name="password" placeholder="Votre mot de passe">
				<input type="submit" value="Connexion">
		
			</form>
		</c:if>
		
		<c:forEach var="message" items="${requestScope.message}">
		
		<p class="error">${message}</p>
		
		</c:forEach>

		
		<p>
			${sessionScope.connect}
		</p>
		
		<a href="${pageContext.request.contextPath}/register">Créer un compte</a>
		
		<%@ include file="/WEB-INF/fragments/footer.jspf"%>
		
		
	</body>
	
</html>