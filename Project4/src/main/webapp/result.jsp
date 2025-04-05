<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,br.edu.ifspcjo.ads.web2.project3.model.Person"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Lista das pessoas Cadastradas</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<c:choose>
		<c:when test="${fn:length(peopleList)>0}">
			<table>
				<thead>
					<tr>
						<th>#</th>
						<th>Nome</th>
						<th>CPF</th>
						<th>E-mail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="person" items="${peopleList}" varStatus="cont">
						<tr>
							<td>${cont.count}</td>
							<td>${person.name}</td>
							<td>${person.cpf}</td>
							<td>${person.email}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<c:out value="Nenhuma pessoa cadastrada."></c:out>
		</c:otherwise>
	</c:choose>
</body>
</html>