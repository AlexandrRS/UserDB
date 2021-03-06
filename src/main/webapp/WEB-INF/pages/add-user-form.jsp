<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Add user page</title>
    </head>
    <body>
        <p><a href="${pageContext.request.contextPath}/list/page1.html&filter=">List of Users</a> | <a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
        <h1>Add user page</h1>
        <p>Fill form and press add</p>
        <form:form method="POST" commandName="filter" action="${pageContext.request.contextPath}/add.html">
            <table>
                <tbody>
                    <tr>
                        <td>Name:</td>
                        <td><form:input path="name" /></td>
                    </tr>
                    <tr>
                        <td>Age:</td>
                        <td><form:input path="age" /></td>
                    </tr>
                    <tr>
                        <td>This user is Admin?</td>
                        <td><form:checkbox path="isAdmin" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="add" /></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </body>
</html>