<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome to Login Page!</title>
</head>
<body>

<h3>Login</h3>

<form:form id="loginForm" modelAttribute="user" action="login" method="post">

    <table align="center">

    <tr>
        <td>
            <form:label path="email">Email</form:label>
        </td>
        <td>
            <form:input path="email" name="email" id="email" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="password">Password</form:label>
        </td>
        <td>
            <form:password path="password" name="password" id="password" />
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <form:button id="login" name="login">Login</form:button>
        </td>
    </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="/LibraryFive_war_exploded/login/register">Doesn't have an account? Please, click here to register!</a>
            </td>
        </tr>
    </table>
</form:form>

<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
    </tr>
</table>

</body>
</html>
