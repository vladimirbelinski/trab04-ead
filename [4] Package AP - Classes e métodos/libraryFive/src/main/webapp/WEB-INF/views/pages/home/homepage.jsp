<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Hello! Welcome to LibraryFive!</title>
</head>
<body>

<h3> Welcome to LibraryFive, ${user.name}!</h3>

<table>
    <c:forEach items="${users}" var="item">
        <tr>
            <td><c:out value="Nome: ${item.name}"/></td>
        </tr>
    </c:forEach>
</table>

</br>
</br>

<form action="/logout">
    <input type="submit" value="Logout"/>
</form>

</br>
</br>

<form action="delete" method="post">
    <input type="hidden" name="actualUserEmail" value="${user.email}">
    <button type="submit">Deletar usuário</button>
</form>

</body>
</html>
