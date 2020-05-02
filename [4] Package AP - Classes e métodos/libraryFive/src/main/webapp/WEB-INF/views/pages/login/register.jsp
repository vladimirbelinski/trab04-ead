<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome to Register Page!</title>
</head>
<body>

<h3>Register Form</h3>

<form:form id="regForm" modelAttribute="user" action="register" method="post">

    <table align="center">

        <tr>
            <td>
                <form:label path="name">Nome</form:label>
            </td>
            <td>
                <form:input path="name" name="name" id="name" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">Senha</form:label>
            </td>
            <td>
                <form:password path="password" name="password" id="password" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">Email</form:label>
            </td>
            <td>
                <form:input type="email" path="email" name="email" id="email" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="userType">Tipo de usuário</form:label>
            </td>
            <td>
                Aluno: <form:radiobutton path="userType" value="ALUNO"/> <br/>
                Servidor: <form:radiobutton path="userType" value="SERVIDOR"/></td>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="street">Rua</form:label>
            </td>
            <td>
                <form:input path="street" name="street" id="street" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="neighborhood">Bairro</form:label>
            </td>
            <td>
                <form:input path="neighborhood" name="neighborhood" id="neighborhood" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="streetNumber">Número da rua</form:label>
            </td>
            <td>
                <form:input type="number" inputmode="numeric" path="streetNumber" name="streetNumber" id="streetNumber" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="additionalAddress">Complemento</form:label>
            </td>
            <td>
                <form:input path="additionalAddress" name="additionalAddress" id="additionalAddress" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="state">Estado</form:label>
            </td>
            <td>
                <form:input path="state" name="state" id="state" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="city">Cidade</form:label>
            </td>
            <td>
                <form:input path="city" name="city" id="city" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="birthDate">Data de nascimento</form:label>
            </td>
            <td>
                <form:input type="text" placeholder="dd/MM/yyyy" path="birthDate" name="birthDate" id="birthDate" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="userStatus">Status do usuário</form:label>
            </td>
            <td>
                Ativo: <form:radiobutton path="userStatus" value="ATIVO"/> <br/>
                Inativo: <form:radiobutton path="userStatus" value="INATIVO"/></td>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="admin">Admin?</form:label>
            </td>
            <td>
                Sim <form:radiobutton path="admin" value="true"/> <br/>
                Não <form:radiobutton path="admin" value="false"/></td>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:button id="register" name="register">Register</form:button>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
