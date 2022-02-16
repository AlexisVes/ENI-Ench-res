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
		
		<c:if test="${sessionScope.connect == null}">
			<a href="${pageContext.request.contextPath}/login"> Se connecter</a>
		</c:if>
		
		
		<c:if test="${sessionScope.connect != null }">
			<a href="${pageContext.request.contextPath}/home?param=disconnect"> Se déconnecter</a>
		</c:if>
		
		
		<h1>Liste des enchères</h1>
		<!-- S'appuyer sur la liste pour afficher les articles disponibles à la vente -->
		<c:forEach var="article" items="${requestScope.listeArticles}">
			<h2>${article.nomArticle}</h2>
			<p>Prix : ${article.prixVente}</p>
			<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
			
			<p> Vendeur : 
			<c:if test="${sessionScope.connect == null}">
				${article.pseudo}
			</c:if>
			
			<c:if test="${sessionScope.connect != null}">
				<a href="${pageContext.request.contextPath}/connect/profil?pseudo=${article.pseudo}" >${article.pseudo}</a>
			</c:if>
			</p>
		</c:forEach>
		
	</body>
</html>