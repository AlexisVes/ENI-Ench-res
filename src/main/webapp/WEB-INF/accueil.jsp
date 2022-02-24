<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
					<!-- On affiche le menu Admin si l'utilisateur connecté est administrateur -->
					<c:if test="${requestScope.admin == 1 }">
						<a href="${pageContext.request.contextPath}/administrator/admin">Admin</a>
					</c:if>
					<a href="${pageContext.request.contextPath}/connect/sell_article?pseudo=${sessionScope.connect}"> Vendre un article</a>
					<a href="${pageContext.request.contextPath}/connect/profil?pseudo=${sessionScope.connect}"> Mon profil</a>
					<a href="${pageContext.request.contextPath}/home?disconnect=disconnect"> Se déconnecter</a>
				</c:if>
			</div>
		</nav>
		
		<p class="jaune"> ${requestScope.confirmation } </p>
		
		<h2>Liste des enchères</h2>
		
		<form class="navigation_filters" action="${pageContext.request.contextPath}/home" method="post" >
		
			<input type="text" name="search" placeholder="Rechercher">
			
			<select name="categorie">
			
			<option value="0"> Toutes catégories </option>
			
			<c:forEach var="categorie" items="${requestScope.listeCategories}">
				<option value="${categorie.noCategorie}"> ${categorie.libelle} </option>
			</c:forEach>
			
			</select>
			
			<c:if test="${sessionScope.connect != null }">
			
				<div class="select_filters">	
						
					<c:if test="${requestScope.achat != null }">
						<input type="radio"  name="achat" value="achat" checked>
					</c:if>
					<c:if test="${requestScope.achat == null }">
						<input type="radio"  name="achat" value="achat">
					</c:if>
					
					<label for="achat"> Achat </label>
					
					<c:if test="${requestScope.achat != null }">
						
						<input type="checkbox" name="encheres_ouvertes">
		  				<label for="encheres_ouvertes">Enchères ouvertes</label>
		  				
		  				<input type="checkbox" name="mes_encheres">
		  				<label for="mes_encheres">Mes enchères</label>
		  				
		  				<input type="checkbox" name="mes_encheres_remportees">
		  				<label for="mes_encheres_remportees">Mes enchères remportées</label>
				
					</c:if>
				</div>
				
				<div class="select_filters">
					<c:if test="${requestScope.vente != null }">
						<input type="radio"  name="achat" value="vente" checked>
					</c:if>
					
					<c:if test="${requestScope.vente == null }">
						<input type="radio"  name="achat" value="vente">
					</c:if>	
					
					
					<label for="vente"> Mes ventes </label>
					
					<c:if test="${requestScope.vente != null }">
					
						<input type="checkbox" name="mes_ventes_ouvertes">
			  			<label for="mes_ventes_ouvertes">Mes ventes en cours</label>
			  				
			  			<input type="checkbox" name="mes_ventes_futur">
			  			<label for="mes_ventes_futur">Ventes non débutées</label>
			  				
			  			<input type="checkbox" name="ventes_terminees">
			  			<label for="ventes_terminees">Ventes terminées</label>
					
					</c:if>
				
				</div>
			
			</c:if>
			
			<input type="submit" value="Rechercher">
		</form>

		<!-- S'appuyer sur la liste pour afficher les articles disponibles à la vente -->
		<div class="col">
			<c:forEach var="article" items="${requestScope.listeArticles}">
				<article>
					<h3><a href="${pageContext.request.contextPath}/connect/sell_details?nomArticle=${article.nomArticle}">${article.nomArticle}</a></h3>
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
		</div>
		<%@ include file="/WEB-INF/fragments/footer.jspf"%>
	</body>
</html>