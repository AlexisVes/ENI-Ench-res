<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Mettre un nouvel article en vente</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	</head>
	<body>
	
	
		<a href="${pageContext.request.contextPath}/home" ><img src="${pageContext.request.contextPath}/img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
		<h2>Nouvelle vente</h2>
		
		
		<form action="${pageContext.request.contextPath}/connect/sell_article?user=${requestScope.userProfil.userId}" class="grid" method="post" accept-charset="utf-8">
		
			<label for="article"> Article :</label>
			<input type="text" name="article">
		
			<label for="description"> Description :</label>
			<input type="text" name="description">
		
			<label for="categorieSelect"> Catégorie </label>
			
			
			<select name="categorieSelect">
			
			   <c:forEach var="categorie" items="${requestScope.listeCategories}">
						<option value="${categorie.noCategorie}"> ${categorie.libelle} </option>
				</c:forEach>
				
			</select>
		
			<label for="photoArticle"> Photo de l'article</label>
			<input type="file" name="photoArticle" class="photo_article" accept="image/png, image/jpeg">
		
			<label for="miseAPrix"> Mise à prix :</label>
			<input type="number" name="miseAPrix">
		
			<label for="debutEnchere"> Début de l'enchère :</label>
			<input type="date" name="debutEnchere">
		
			<label for="finEnchere"> Fin de l'enchère :</label>
			<input type="date" name="finEnchere">
			
			
			<h2>Retrait</h2>
			<label for="rue"> Rue :</label>
			<input type="text" name="rue" value="${requestScope.userProfil.rue}">
			
			<label for="codePostal"> Code postal :</label>
			<input type="text" name="codePostal" value="${requestScope.userProfil.codePostal}">
			
			<label for="ville"> Ville :</label>
			<input type="text" name="ville" value="${requestScope.userProfil.ville}">
			
			
			<input type="submit" value="Valider">
			
		</form>
	
	</body>
</html>