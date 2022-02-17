<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	</head>
	<body>

		<nav>
			<a href="${pageContext.request.contextPath}/home" ><img src="./img/enibay_logo.png" height="200px" width="auto" alt="logo enibay"></a>
		

			<c:if test="${sessionScope.connect == null}">
				<a href="${pageContext.request.contextPath}/login">S'inscrire - Se connecter</a>
			</c:if>
			
			<c:if test="${sessionScope.connect != null }">
				<a href="${pageContext.request.contextPath}/home?param=disconnect"> Se déconnecter</a>
			</c:if>
			
			<c:if test="${sessionScope.connect != null }">
				<a href="${pageContext.request.contextPath}/connect/profil?pseudo=${sessionScope.connect}"> Mon profil</a>
			</c:if>
		</nav>
		
		
		<h2>Liste des enchères</h2>
		<!-- S'appuyer sur la liste pour afficher les articles disponibles à la vente -->
		<c:forEach var="article" items="${requestScope.listeArticles}">
			<h3>${article.nomArticle}</h3>
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
		<%@ include file="/WEB-INF/fragments/footer.jspf"%>
	</body>
</html>