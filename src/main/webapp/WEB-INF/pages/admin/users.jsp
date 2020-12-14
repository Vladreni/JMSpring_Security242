<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Lists of Users</title>
</head>
<body>
<h1>Welcome to users' page, admin</h1>
<br/>
<div>
<table border="5">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PASSWORD</th>
        <th>ROLES</th>
    </tr>
    <tr th:each="user : ${usersList}">
        <td th:utext="${user.getId()}">Id</td>
        <td><a th:href="@{/admin/{id}(id=${user.getId()})}"><p th:utext="${user.getName()}">name</p></a></td>
        <td th:utext="${user.getPassword()}">Password</td>
        <td th:each="role : ${user.getRoles()}" th:utext="${role.getRole()}">Roles</td>

    </tr>
</table>
</div>
<br/>
<hr/>
<form th:action="@{/admin/new}" th:method="get">
    <input  class="button button-block" type="submit" value="Go to create new user!"/>
</form>
<br/>
<a href="/logout">Logout</a>
</body>
</html>
