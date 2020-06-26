<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ToDo" %>
<%@ page import="myUtil.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
<body class="body_2">
<a class="logout" href="/logout">logout</a>
<%
    List<ToDo> toDoList = (List<ToDo>) request.getAttribute("toDoList");
    User user = (User) session.getAttribute("user");
    List<User> users = (List<User>) request.getAttribute("allUsers");

%>

<div class="user"><h2> WELCOME ADMIN<br>
    <%= user.getName()%> <%=user.getSurname()%>
</h2>

    <% if (user.getPictureUrl() != null) { %>
    <img class="user_photo" src="/image?path=<%=user.getPictureUrl()%>" width="200"/> <%} else {%>

    <img class="user_photo" src="/image/man.jpg"/>

    <%}%>
</div>

<%
    String mes = "";
    if (session.getAttribute("mes") != null) {
        mes = (String) session.getAttribute("mes");
        session.removeAttribute("mes");

    }
%>
<p style="color: red"><%=mes%>
</p>

<div class="control">
    <div class="all_users">
        <h2 style="margin-left: 80px;">All Users</h2>
        <%for (User user1 : users) { %>
        <ul>
            <li><%=user1.getId()%>-<%= user1.getName()%>
                <%= user1.getSurname() %>
            </li>
            <li><%= user1.getEmail() %> <%= user1.getUserType() %>
            </li>
            <li>
                <a class="delete" href="/removeUser?id=<%= user1.getId() %>">delete</a></form>
            </li>
        </ul>
        <% } %>
    </div>
    <div class="register">
        <h2 style="margin-left: 80px; margin-top: 50px">Add User</h2>
        <form action="/register" method="post" enctype="multipart/form-data">
            <input class="input_for_admin" name="name" type="text " placeholder="NAME"/><br>
            <input class="input_for_admin" name="surname" type="text" placeholder="SURNAME"/><br>
            <input class="input_for_admin" name="email" type="text" placeholder="EMAIL"/><br>
            <input class="input_for_admin" name="password" type="text" placeholder="PASSWORD"/><br>
            <input name="image" type="file"/><br>
            <input type="submit" value="create"/>
        </form>
    </div>
    <div class="add_todo">
        <h2 style="margin-left: 80px; margin-top: -145px">Add Todo</h2>
        <form action="/task" method="post" enctype="multipart/form-data">
            <input class="input_for_admin" name="name" type="tex" placeholder="NAME"/><br>
            <input class="input_for_admin" name="description" type="text" placeholder="DESCRIPTION"/><br>
            <input class="input_for_admin" name="deadline" type="date"/><br>
            <input class="input_for_admin" name="status" type="text" placeholder="STATUE"/><br>
            <input class="input_for_admin" name="userId" type="text" placeholder="USER ID"/><br>
            <input name="pictureUrl" type="file"/><br>
            <input type="submit" value="create"/>
        </form>
    </div>
</div>
<% int count = 0;
    for (ToDo toDo : toDoList) {
        count++;
%>
<%if (count % 2 == 0) {%>
<div class="left_todo2">
    <ul><a class="todo_name" href="/commentHome?toDoId=<%=toDo.getId()%>"><%=toDo.getName()%>
    </a>
        <div class="changeTaskImage_block">
            <li><% if (toDo.getPictureUrl() != null) { %>
                <img class="todo_photo" src="/image?path=<%=toDo.getPictureUrl()%>"/> <%}else {%>
                <img class="todo_photo" src="/image/na.jpg"/>
                    <%}%>

                <div class="changeTaskImage">
                    <form action="/changeTaskImage" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="toDoId" value="<%=toDo.getId()%>">
                        <input name="pictureUrl" type="file"/><br>
                        <input type="submit" value="change photo"/>
                    </form>
                </div>
        </div>
        </li>

        <li><%=DateUtil.convertDateToString(toDo.getCreatedDate())%>
            --<%=DateUtil.convertDateToString(toDo.getDeadline())%>
        </li>
        <li class="changeTaskStatus_getStatus"><%=toDo.getStatus()%>
        </li>
        <li><%=toDo.getDescription()%>
        </li>
    </ul>
</div>
<%
} else {%>
<div class="right_todo2">
    <ul><a class="todo_name" href="/commentHome?toDoId=<%=toDo.getId()%>"><%=toDo.getName()%>
    </a>
        <div class="changeTaskImage_block">
            <li><% if (toDo.getPictureUrl() != null) { %>
                <img class="todo_photo" src="/image?path=<%=toDo.getPictureUrl()%>"/> <%}else {%>
                <img class="todo_photo" src="/image/na.jpg"/>
                    <%}%>

                <div class="changeTaskImage">
                    <form action="/changeTaskImage" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="toDoId" value="<%=toDo.getId()%>">
                        <input name="pictureUrl" type="file"/><br>
                        <input type="submit" value="change photo"/>
                    </form>
                </div>
        </div>
        </li>

        <li><%=DateUtil.convertDateToString(toDo.getCreatedDate())%>
            --<%=DateUtil.convertDateToString(toDo.getDeadline())%>
        </li>
        <li class="changeTaskStatus_getStatus"><%=toDo.getStatus()%>
        </li>
        <li><%=toDo.getDescription()%>
        </li>
    </ul>
</div>

<% }
}
%>
</body>
</html>
