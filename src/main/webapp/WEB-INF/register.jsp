<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mon profil</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	</head>
<body>

<<<<<<< HEAD
	<a href="${pageContext.request.contextPath}/home" ><img src="./img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
=======
	<a href="${pageContext.request.contextPath}/home" ><img src="./img/enibay_logo.png" height="200px" width="auto" alt="logo enibay"></a>
>>>>>>> branch 'main' of https://github.com/AlexisVes/ENI-Ench-res.git

	<h1>Mon Profil</h1>
	<form action="${pageContext.request.contextPath}/register" method="Post" >
		
		<label for="pseudo"> Votre pseudo :</label>
		<input type="text" name="pseudo" placeholder="Votre pseudo">
		
		<label for="password"> Votre mot de passe :</label>
		<input type="password" name="password">
		
		<label for="nom"> Votre nom :</label>
		<input type="text" name="nom" placeholder="Votre nom">
		
		<label for="prenom"> Votre prenom :</label>
		<input type="text" name="prenom" placeholder="Votre prenom">
		
		<label for="email"> Votre email :</label>
		<input type="email" name="email" placeholder="Votre email">
		
		<label for="tel"> Votre telephone :</label>
		<input type="text" name="tel" placeholder="Votre telephone">
		
		<label for="rue"> Votre rue :</label>
		<input type="text" name="rue" placeholder="Votre rue">
		
		<label for="code_postal"> Votre code postal :</label>
		<input type="text" name="code_postal" placeholder="Votre code postal">
		
		<label for="ville"> Votre ville :</label>
		<input type="text" name="ville" placeholder="Votre ville">
		
		<input type="submit" value="Valider">
		
		<a href="${pageContext.request.contextPath}/home">Annuler</a>
		
		<c:if test="${requestScope.message != null }">
			<p class="error"> Les erreurs sont les suivantes: </p>
		</c:if>
		
		<c:forEach var="message" items="${requestScope.message}">
		
			<p class="error"> ${message}</p>
		
		</c:forEach>
		
	</form>
	
	<%@ include file="/WEB-INF/fragments/footer.jspf"%>
</body>
</html>