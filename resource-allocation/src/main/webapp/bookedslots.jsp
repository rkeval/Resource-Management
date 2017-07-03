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
<h3>Projector Details</h3><br>
<c:if test="${not empty assignments}">
			<c:forEach var="assignment" items="${assignments}">
				Allotted To: ${assignment.getTeam().getName()}<br>
				Projector Name: ${assignment.getProjector().getName()}<br>
				Requested Time: ${assignment.getRequestedTime()}<br>
				Start Time: ${assignment.getStartTime()}<br>
				End Time: ${assignment.getEndTime()}<br>
				<a href = "cancelProjectorRequest.html?id=${assignment.getId()}">Cancel Request</a>|
				<a href = "returnProjector.html?id=${assignment.getId()}">Return Projector</a><br><br>
				-----------------------------------------------------------------------------------<br><br>
			</c:forEach>
</c:if>
</body>
</html>