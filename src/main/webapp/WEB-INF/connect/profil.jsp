<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profil</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	</head>
	<body>
	
	<a href="${pageContext.request.contextPath}/home" ><img src="../img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
	
	<h2>Profil</h2>
	<p>Pseudo :			${requestScope.userProfil.pseudo}</p>
	<p>Nom :  			${requestScope.userProfil.nom}</p>
	<p>Prenom :  		${requestScope.userProfil.prenom}</p>
	<p>Email :  		${requestScope.userProfil.email}</p>
	<p>Téléphone :  	${requestScope.userProfil.tel}</p>
	<p>Rue :  			${requestScope.userProfil.rue}</p>
	<p>Code Postal :  	${requestScope.userProfil.codePostal}</p>
	<p>Ville :  		${requestScope.userProfil.ville}</p>
	
	<c:if test="${sessionScope.connect eq requestScope.userProfil.pseudo}">
	
		<a href="${pageContext.request.contextPath}/connect/update_profil?pseudo=${requestScope.userProfil.pseudo}"> Modifier </a>
		
	</c:if>
	
	<%@ include file="/WEB-INF/fragments/footer.jspf"%>
	
	</body>
</html>