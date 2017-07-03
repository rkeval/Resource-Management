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
<c:if test="${empty projectors}">
There is no printer available on your requested time. Please try with other time.<br><br>
<jsp:include page="/checkAvailability.jsp"></jsp:include>  
</c:if>
<c:if test="${not empty projectors}">
<h3>Available Printers List:</h3>
			<c:forEach var="projector" items="${projectors}">
				Name: ${projector.getName()}<br>
				Id: ${projector.getId()}<br>
				Active Status: ${projector.isActive()}<br>
				-----------------------------------------------------<br><br>
			</c:forEach>
</c:if>

</body>
</html>