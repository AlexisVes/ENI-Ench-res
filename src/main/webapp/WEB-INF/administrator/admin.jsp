<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>

	<a href="${pageContext.request.contextPath}/home" ><img src="../img/enibay_logo.png" alt="logo enibay" class="logo_enibay"></a>
	
	<h1 class="admin-title">Administration - Gestion des utilisateurs</h1>
	
	<!-- Création d'un tableau regroupant les users existants en base -->
	
	<table class=user_table>
		<tr>
			<td class ="admin-heading">Pseudo</td>
			<td class ="admin-heading">Nom</td>
			<td class ="admin-heading">Prenom</td>
			<td class ="admin-heading">E-mail</td>
			<td class ="admin-heading">Téléphone</td>
			<td class ="admin-heading">Rue</td>
			<td class ="admin-heading">Code Postal</td>
			<td class ="admin-heading">Ville</td>
			<td class ="admin-heading">Crédits</td>
			<td class="admin-heading admin-delete">Delete</td>
		</tr>
		<c:forEach items="${requestScope.allUsers}" var="item">
		<tr>
			<td class="heading-one">${item.pseudo}</td>
			<td class="heading-two">${item.nom}</td>
			<td class="heading-one">${item.prenom}</td> 
			<td class="heading-two">${item.email}</td>
			<td class="heading-one">${item.tel}</td>
			<td class="heading-two">${item.rue}</td>
			<td class="heading-one">${item.codePostal}</td>
			<td class="heading-two">${item.ville}</td>
			<td class="heading-one">${item.credit}</td>
			<td class="delete-td"><a href="${pageContext.request.contextPath}/connect/delete_user?utilisateur=${item.pseudo}"><img src ="../img/admin-delete.png" alt="delete icon" class="delete-logo"></a><td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>