<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://thymeleaf.org">
<head>
    <title>New User</title>
</head>
<body>
<form th:action="@{/admin}" th:method="post" th:object="${newUser}">
    <label for="name">Enter name: </label>
    <input type="text" th:field="*{name}" id="name"/>
    <br/>
    <label for="pass">Enter password: </label>
    <input type="text" th:field="*{password}" id="pass"/>
    <br/>
    <label for="admin"  >ROLE_ADMIN</label>
    <input type="checkbox" id="admin" name="admin" th:value="ROLE_ADMIN"/>

    <!--    <p th:each="role : {newUser}" th:utext="{role}">Roles</p>


   <th:block th:each="role : {newUser.getRoles()}">
    <label th:utext="{role.getRole()}">d
        <input type="checkbox" />
    </label>
    </th:block>

    <select id="editRoles" name="roles" multiple size="2" required class="form-control form-control-sm">
        <option value="ROLE_ADMIN" adminSelect >ROLE_ADMIN</option>
        <option value="ROLE_USER"   userSelect >ROLE_USER</option>
    </select> -->
 <br/>
    <input type="submit" value="Create"/>
</form>
<br/>
<a href="/logout">Logout</a>
</body>
</html>
