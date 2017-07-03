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
<h3>Latest Available Projectors:</h3>
<c:if test="${not empty assignments}">
			<c:forEach var="assignment" items="${assignments}">
				Name: ${assignment.getProjector().getName()}<br>
				Id: ${assignment.getProjector().getId()}<br>
				Active Status: ${assignment.getProjector().isActive()}<br>
				Latest Available Time: ${assignment.getStartTime()}<br>
				-----------------------------------------------------<br><br>
			</c:forEach>
</c:if>
</body>
</html>