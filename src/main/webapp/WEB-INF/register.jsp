<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mon profil</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	</head>
<body>

	<a href="${pageContext.request.contextPath}/home" ><img src="./img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>

	<h1>Mon Profil</h1>
	<form action="${pageContext.request.contextPath}/register" method="Post" class="grid">
		
		<label for="pseudo"> Pseudo :</label>
		<input type="text" name="pseudo" placeholder="Votre pseudo"><br>
		
		<label for="password"> Mot de passe :</label>
		<input type="password" name="password" placeholder="Votre mot de passe"><br>
		
		<label for="nom"> Nom :</label>
		<input type="text" name="nom" placeholder="Votre nom"><br>
		
		<label for="prenom"> Prenom :</label>
		<input type="text" name="prenom" placeholder="Votre prenom"><br>
		
		<label for="email"> Email :</label>
		<input type="email" name="email" placeholder="Votre email"><br>
		
		<label for="tel"> Téléphone :</label>
		<input type="text" name="tel" placeholder="Votre telephone"><br>
		
		<label for="rue"> Rue :</label>
		<input type="text" name="rue" placeholder="Votre rue"><br>
		
		<label for="code_postal"> Code postal :</label>
		<input type="text" name="code_postal" placeholder="Votre code postal"><br>
		
		<label for="ville"> Ville :</label>
		<input type="text" name="ville" placeholder="Votre ville"><br><br>
		
		<input type="submit" value="Valider" class="button">
		
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