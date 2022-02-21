<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Ventes</title>
</head>
<body>
	<h1>Détail Ventes</h1>
	
	<p>${requestScope.article.nomArticle}</p>
	<p>Description : ${requestScope.article.description}</p>
	<p>Catégorie : ${requestScope.libelleCategorie}</p>
	<p>Meilleure offre : </p>
	<p>Mise à prix : ${requestScope.article.prixInitial}</p>
	<p>Fin de l'enchère : ${requestScope.article.dateFinEncheres}</p>
	<p>Retrait : ${requestScope.retrait.rue}</p>
	<p>${requestScope.retrait.codePostal} ${requestScope.retrait.ville}</p>
	<p>Vendeur : ${requestScope.artile.pseudo}
	


</body>
</html>