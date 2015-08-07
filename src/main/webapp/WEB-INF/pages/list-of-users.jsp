<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<p><a href="${pageContext.request.contextPath}/add.html">Add new user</a>
		| <a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
		<title>List of users</title>
	</head>
	<body>
		<h1>List of users</h1>
		<p>${message}</p>

		<form:form method="POST" commandName="filter" action="${pageContext.request.contextPath}/list/page1.html&filter=">
        	<table>
				<tbody>
					<tr>
					    <td><a href="${pageContext.request.contextPath}/list/page1.html&filter=">drop filter</a></td>
						<td>Name:<form:input path="name" /></td>
						<td><input type="submit" value="filter now!" /></td>
					</tr>
				</tbody>
        	</table>
        </form:form>
		<table border="1px" cellpadding="0" cellspacing="0" >
			<thead>
				<tr>
					<th width="10%">id</th>
					<th width="15%">name</th>
					<th width="10%">age</th>
					<th width="10%">permissions</th>
					<th width="10%">date of creation</th>
					<th width="10%">actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td>${user.age}</td>
						<td>
						    <c:choose>
                                <c:when test="${user.isAdmin eq 'true'}">
                                    Administrator
                                </c:when>
                                <c:otherwise>
                                    User
                                </c:otherwise>
                            </c:choose>
						</td>
						<td>${user.createDate}</td>
						<td>
							<a href="${pageContext.request.contextPath}/edit/${user.id}.html">Edit</a><br/>
							<a href="${pageContext.request.contextPath}/delete/${user.id}.html">Delete</a><br/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		pages:
		<c:forEach var="i" begin="1" end="${pagecount}">
		    <a href="${pageContext.request.contextPath}/list/page${i}.html&filter=${filter.name}">${i}</a>
        </c:forEach>
	</body>
</html>