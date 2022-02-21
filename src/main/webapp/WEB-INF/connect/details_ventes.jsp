<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Ventes</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>

	<a href="${pageContext.request.contextPath}/home" ><img src="../img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>

	<h1>Détail de la vente</h1>
	
	<p>${requestScope.article.nomArticle}</p>
	<p><span class="detail_vente">Description :</span> ${requestScope.article.description}</p>
	<p><span class="detail_vente">Catégorie :</span> ${requestScope.libelleCategorie}</p>
	<p><span class="detail_vente">Meilleure offre :</span> 
	<c:if test="${requestScope.enchere != null}">
		${requestScope.enchere} crédits par ${requestScope.encherisseur}
	</c:if>
	</p>
	<p><span class="detail_vente">Mise à prix :</span> ${requestScope.article.prixInitial}</p>
	<p><span class="detail_vente">Fin de l'enchère :</span> ${requestScope.article.dateFinEncheres}</p>
	<p><span class="detail_vente">Retrait :</span> ${requestScope.retrait.rue} ${requestScope.retrait.codePostal} ${requestScope.retrait.ville} </p>
	<p><span class="detail_vente">Vendeur :</span> ${sessionScope.connect}</p>
	
	
	<form action="${pageContext.request.contextPath}/connect/sell_details?nomArticle=${requestScope.article.nomArticle}" method="post">
	
		<label for="proposition"><span class="detail_vente"> Ma proposition :</span></label>
			
		<c:if test="${requestScope.enchere != null}">
			<input type="number" name="proposition" placeholder="min : ${requestScope.enchere}" required="required" min="${requestScope.enchere}">
		</c:if>
			
		<c:if test="${requestScope.enchere == null}">
			<input type="number" name="proposition" placeholder="min : ${requestScope.article.prixVente}" required="required" min="${requestScope.article.prixVente}">
		</c:if>
			
		<input type="submit" value="Valider">
	</form>
</body>
</html>