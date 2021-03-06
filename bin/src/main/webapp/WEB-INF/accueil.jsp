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
			<a href="${pageContext.request.contextPath}/home" ><img src="${pageContext.request.contextPath}/img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
		
			<div class="liens-nav">
				<c:if test="${sessionScope.connect == null}">
					<a href="${pageContext.request.contextPath}/login" class="enibay_link">S'inscrire - Se connecter</a>
				</c:if>
				
				<c:if test="${ sessionScope.connect != null }">
					<a href="${pageContext.request.contextPath}/connect/sell_article?pseudo=${sessionScope.connect}"> Vendre un article</a>
					<a href="${pageContext.request.contextPath}/connect/profil?pseudo=${sessionScope.connect}"> Mon profil</a>
					<a href="${pageContext.request.contextPath}/home?param=disconnect"> Se déconnecter</a>
				</c:if>
			</div>
			
		</nav>
		
		<p class="jaune"> ${requestScope.confirmation } </p>
		
		<h2>Liste des enchères</h2>
		
		<form action="${pageContext.request.contextPath}/home" method="post">
		
			<input type="text" name="search" placeholder="Rechercher">
			
			<select name="categorie">
			
			<option value="0"> Toutes catégories </option>
			
			<c:forEach var="categorie" items="${requestScope.listeCategories}">
				<option value="${categorie.noCategorie}"> ${categorie.libelle} </option>
			</c:forEach>
			
			</select>
			
			<c:if test="${sessionScope.connect != null }">
			
				<input type="radio"  name="achat" value="achat">
				<label for="achat"> Achat </label>
				
				<c:if test="${requestScope.achat != null }">
					
					<input type="checkbox" name="encheres_ouvertes">
	  				<label for="encheres_ouvertes">Enchères ouvertes</label>
	  				
	  				<input type="checkbox" name="mes_encheres">
	  				<label for="mes_encheres">Mes enchères</label>
	  				
	  				<input type="checkbox" name="mes_encheres_remportees">
	  				<label for="mes_encheres_remportees">Mes enchères remportées</label>
				
				</c:if>
				
				<input type="radio"  name="vente" value="vente">
				<label for="vente"> Mes ventes </label>
				
				<c:if test="${requestScope.vente != null }">
				
					<input type="checkbox" name="mes_ventes_ouvertes">
		  			<label for="mes_ventes_ouvertes">Mes ventes en cours</label>
		  				
		  			<input type="checkbox" name="mes_ventes_futur">
		  			<label for="mes_ventes_futur">Ventes non débutées</label>
		  				
		  			<input type="checkbox" name="ventes_terminees">
		  			<label for="ventes_terminees">Ventes terminées</label>
				
				</c:if>
			
			</c:if>
			
			<input type="submit" value="Rechercher">
		
		</form>
		
		<!-- S'appuyer sur la liste pour afficher les articles disponibles à la vente -->
		<c:forEach var="article" items="${requestScope.listeArticles}">
			<article>
				<h3><a href="">${article.nomArticle}</a></h3>
				<p>Prix : ${article.prixVente}</p>
				<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
				
				<p> Vendeur : 
				<c:if test="${sessionScope.connect == null}">
					${article.pseudo}
				</c:if>
				
				<c:if test="${sessionScope.connect != null}">
					<a href="${pageContext.request.contextPath}/connect/profil?pseudo=${article.pseudo}" class="enibay_link">${article.pseudo}</a>
				</c:if>
				</p>
			</article>
		</c:forEach>
		<%@ include file="/WEB-INF/fragments/footer.jspf"%>
	</body>
</html>