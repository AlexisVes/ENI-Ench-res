<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
	</head>
	<body>
		
		<a href="${pageContext.request.contextPath}/login"> Se connecter</a>
		
		<h1>Articles disponibles à la vente</h1>
		<!-- S'appuyer sur la liste pour afficher les articles disponibles à la vente -->
		<c:forEach var="article" items="${articlesAvailable}">
			<h2>${article.nomArticle}</h2>
			<p>Prix : ${article.prixVente}</p>
			<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
			<p>Vendeur : A DEFINIR</p>
		</c:forEach>
		
	</body>
</html>