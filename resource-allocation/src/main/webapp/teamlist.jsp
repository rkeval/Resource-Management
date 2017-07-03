<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Teams Detail</h3><br>
<c:if test="${not empty teams}">
			<c:forEach var="team" items="${teams}">
				Name: ${team.getName()}<br>
				Id: ${team.getId()}<br>
				Active Status: ${team.isActive()}<br>
				<a href = "deleteTeam.html?teamId=${team.getId()}">delete</a><br><br>
			</c:forEach>
</c:if>
</body>
</html>