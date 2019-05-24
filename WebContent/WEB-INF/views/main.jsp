<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringJPA</title>
</head>
<body>

	<table>
		<tr>
			<th>Name</th>
			<th>Last Name</th>
			<th>Age</th>
			<th>Status</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
			<c:forEach items="${students}" var="students">
				<tr>
					<form action="${pageContext.request.contextPath}/updateForm" method="post">
						<td>${students.sName}</td>
						<td>${students.lName}</td>
						<td>${students.sAge}</td>
						<td>${students.activoDelegate}</td>
						<td><form action="${pageContext.request.contextPath}/delete" method="post">
						<input type="hidden" name="code" value="${students.cCLiente}">
						<input type="submit" value="editar"/>
					</form></td>
					</form>
					<td>
						<form action="${pageContext.request.contextPath}/delete" method="post">
							<input type="hidden" name="student" value="${students.sName}">
							<input type="submit" value="elimiar"/>
						</form>
					</td>
				</tr>	
			</c:forEach>
	
	</table>
	<form action="${pageContext.request.contextPath}/save" method="post">
		<input type="submit" value="Agregar un nuevo usuario">
	</form>
</body>
</html>