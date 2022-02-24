<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mot de passe oublié</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
	<a href="${pageContext.request.contextPath}/home?email=email" ><img src="${pageContext.request.contextPath}/img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
	<h1>Mot de passe oublié</h1>
	<form action="${pageContext.request.contextPath}/mdp" method="post">
		<label for="email">email</label>
		<input type="email" name="email" placeholder="Entrez votre email">
		<button>Valider</button>
	</form>
	
	<c:if test="${requestScope.email != null}">
		<form action="${pageContext.request.contextPath}/home">
			<label for="mdp">Entrez votre nouveau mod'e path</label>
			<input type="password" name="mdp">
			<button>Valider</button>
		</form>
	</c:if>

</body>
</html>