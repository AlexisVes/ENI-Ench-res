<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	</head>
	<body>
	
	<a href="${pageContext.request.contextPath}/home" ><img src="./img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
	
		<c:if test="${sessionScope.connect == null || sessionScope.connect eq 'Mauvais pseudo ou mot de passe'}">
		
			<form action="${pageContext.request.contextPath}/login" method="Post" >
		
				<label for="pseudo">Pseudo :</label>
				<input type="text" name="pseudo" placeholder="Votre pseudo" value="${cookie.login.value}">
				<label for="password">Mot de passe :</label>
				<input type="password" name="password" placeholder="Votre mot de passe" value="${cookie.password.value}">
				<input type="checkbox" name="souvenir">
				<label for="souvenir"> Se souvenir</label>
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
		<a href="${pageContext.request.contextPath}/mdp"> Mot de passe oublié</a>
		
		<%@ include file="/WEB-INF/fragments/footer.jspf"%>	
		
	</body>
	
</html>