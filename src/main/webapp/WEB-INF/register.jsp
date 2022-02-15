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
		
		<p>${requestScope.message}</p>

		
	</form>
</body>
</html>