<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="team" class="beans.teamBean" scope="application"/>
<body>
<%-- getServletContext().getAttributes("team") <--this gives beam instance --%>
	<h3>teams : ${applicationScope.team.getTeamList()}</h3>
</body>
</html>