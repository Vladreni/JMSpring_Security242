<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://thymeleaf.org">
<head>
    <title>Update user</title>
</head>
<body>
<form th:action="@{/admin/{id}(id=${user.id}) }" th:method="POST" th:object="${user}">
    <label for="name">Enter name: </label>
    <input type="text" th:field="*{name}" id="name"/>
    <br/>
    <label for="pass">Enter password: </label>
    <input type="text" th:field="*{password}" id="pass"/>
    <br/>
    <label for="admin"  >ROLE_ADMIN</label>
    <input type="checkbox" id="admin" name="admin" th:value="ROLE_ADMIN"/>
<!--    <label for="role">Enter Role/Roles: </label>
    <input type="text" th:field="*{roles}" id="role"/>
    <br/>
-->
    <input type="submit" value="Update!"/>
</form>
<br/>
<a href="/logout">Logout</a>
</body>
</html>
