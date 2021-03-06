<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detail Ventes</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	</head>
	<body>
	
		<a href="${pageContext.request.contextPath}/home" ><img src="../img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
	
		<h1>Détail de la vente</h1>
		
		<h2 class="jaune">${requestScope.article.nomArticle}</h2>
		<c:if test="${!empty requestScope.photoArticle}">
			<img  class="photo-article" src="${requestScope.photoArticle}" alt="photo article">
		</c:if>
		<p><span class="detail_vente">Description :</span> ${requestScope.article.description}</p>
		<p><span class="detail_vente">Catégorie :</span> ${requestScope.libelleCategorie}</p>
		
		<p><span class="detail_vente">Meilleure offre :</span> 
		<c:if test="${requestScope.enchere != null}">
			${requestScope.enchere} crédits par ${requestScope.encherisseur} le ${requestScope.dateEnchere }
		</c:if>
		</p>
		
		<p><span class="detail_vente">Mise à prix :</span> ${requestScope.article.prixInitial}</p>
		<p><span class="detail_vente">Fin de l'enchère :</span> ${requestScope.article.dateFinEncheres}</p>
		<p><span class="detail_vente">Retrait :</span> ${requestScope.retrait.rue} ${requestScope.retrait.codePostal} ${requestScope.retrait.ville} </p>
		<p><span class="detail_vente">Vendeur :</span> ${requestScope.nomVendeur.pseudo}</p>
		
		<c:if test="${requestScope.end == null}">
			<form action="${pageContext.request.contextPath}/connect/sell_details?nomArticle=${requestScope.article.nomArticle}" method="post">
			
				<label for="proposition"><span class="detail_vente"> Ma proposition :</span></label>
					
				<c:if test="${requestScope.enchere != null}">
					<input type="number" name="proposition" placeholder="min : ${requestScope.enchere + 1}" required="required" min="${requestScope.enchere + 1}" max="2147483647">
				</c:if>
					
				<c:if test="${requestScope.enchere == null}">
					<input type="number" name="proposition" placeholder="min : ${requestScope.article.prixVente + 1}" required="required" min="${requestScope.article.prixVente + 1}" max="2147483647">
				</c:if>
					
				<input type="submit" value="Valider">
				
			</form>
		</c:if>

			<c:if test="${requestScope.delete != null }">
				<a href="${pageContext.request.contextPath}/connect/delete_article?article=${requestScope.article.nomArticle}"> Supprimer article</a>
			</c:if>
			
			<c:if test="${requestScope.update != null }">
				<a href="${pageContext.request.contextPath}/connect/update_article?article=${requestScope.article.nomArticle}"> Modifier article </a>
			</c:if>
		
		
	</body>
</html>