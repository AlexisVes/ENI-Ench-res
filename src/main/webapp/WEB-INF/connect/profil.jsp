<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
</head>
<body>

<h2>Profil</h2>

<p>Nom :  			${requestScope.userProfil.nom}</p>
<p>Prenom :  		${requestScope.userProfil.prenom}</p>
<p>Email :  		${requestScope.userProfil.email}</p>
<p>Téléphone :  	${requestScope.userProfil.tel}</p>
<p>Rue :  			${requestScope.userProfil.rue}</p>
<p>Code Postal :  	${requestScope.userProfil.codePostal}</p>
<p>Ville :  		${requestScope.userProfil.ville}</p>

<a href="${pageContext.request.contextPath}/home">Accueil</a>

</body>
</html>