<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>

	<body>
	
		<a href="${pageContext.request.contextPath}/home" ><img src="../img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
		
		<form action="" method="post">
		
			<label for= "nomArticle"> Nom de l'article :</label>
			<input type="text" value="${requestScope.article.nomArticle}" name="nomArticle">
			
			<label for= "description"> description :</label>
			<input type="text" value="${requestScope.article.description}" name="description">
			
			<label for= "dateDebut"> Date début enchères :</label>
			<input type="date" value="${requestScope.article.dateDebutEncheres}" name="dateDebut">
			
			<label for= "dateFin"> Date fin enchères :</label>
			<input type="date" value="${requestScope.article.dateFinEncheres}" name="dateFin">
			
			<label for= "prix"> Prix initial :</label>
			<input type="number" value="${requestScope.article.prixInitial}" name="prix">
			
			<input type="submit" value="Valider" name="prix">
		
		</form>
	
	</body>
</html>