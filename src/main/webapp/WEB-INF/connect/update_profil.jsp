<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Modifier profil</h2>
	
	<form action="${pageContext.request.contextPath}/connect/update_profil" method="post">
	
		<label for="pseudo"> Votre pseudo :</label>
		<input type="text" name="pseudo" value="${requestScope.userProfil.pseudo}">
		
		<label for="nom"> Votre nom :</label>
		<input type="text" name="nom" value="${requestScope.userProfil.nom}">
		
		<label for="prenom"> Votre prenom :</label>
		<input type="text" name="prenom" value="${requestScope.userProfil.prenom}">
		
		<label for="email"> Votre email :</label>
		<input type="email" name="email" value="${requestScope.userProfil.email}">
		
		<label for="tel"> Votre telephone :</label>
		<input type="text" name="tel" value="${requestScope.userProfil.tel}">
		
		<label for="rue"> Votre rue :</label>
		<input type="text" name="rue" value="${requestScope.userProfil.rue}">
		
		<label for="code_postal"> Votre code postal :</label>
		<input type="text" name="code_postal" value="${requestScope.userProfil.codePostal}">
		
		<label for="ville"> Votre ville :</label>
		<input type="text" name="ville" value="${requestScope.userProfil.ville}">
		
		<label for="password"> Votre mot de passe :</label>
		<input type="password" name="password" placeholder="Rentrez votre mot de passe">
		
		<label for="password"> Nouveau mot de passe :</label>
		<input type="password" name="password" placeholder="Rentrez votre nouveau mot de passe">
		
		<label for="ville"> Votre crédit :</label>
		<input type="text" name="ville" value="${requestScope.userProfil.credit}" disabled="disabled">
		
		<input type="submit" value="Valider">
	
		<c:forEach var="message" items="${requestScope.message}">
		
			<p class="error"> ${message}</p>
		
		</c:forEach>
	
	</form>

</body>
</html>