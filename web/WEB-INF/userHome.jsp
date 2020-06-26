<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ToDo" %>
<%@ page import="myUtil.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user Home</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">

    <%--
        <meta http-equiv="refresh" content="5">
        էջից դուրս քցելու համար
     --%>
</head>
<body class="body_2">
<a class="logout" href="/logout">logout</a>
<%
    List<ToDo> toDoList = (List<ToDo>) request.getAttribute("toDoList");
    User user = (User) session.getAttribute("user"); %>

<div class="user"><h2> WELCOME <br>
    <%=   user.getName()%> <%=user.getSurname()%>
</h2>

    <% if (user.getPictureUrl() != null) { %>
    <img class="user_photo" src="/image?path=<%=user.getPictureUrl()%>" width="200"/> <%} else {%>
    <img class="user_photo" src="/image/man.jpg"/>

    <%}%>
</div>

<% int count = 0;
    for (ToDo toDo : toDoList) {
        count++;
%>
<%if (count % 2 == 0) {%>
<div class="left_todo">
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
        <div class="changeTaskStatus">
            <form action="/changeTaskStatus" method="get">
                <input type="hidden" name="toDoId" value="<%=toDo.getId()%>">
                <select name="status">
                    <option value="TODO">TODO</option>
                    <option value="FINISHED">FINISHED</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                </select> <input type="submit" value="Update">
            </form>
        </div>
    </li>
    <li><%=toDo.getDescription()%>
    </li>
</ul>
</div>
<%
} else {%>
<div class="right_todo">
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
            <div class="changeTaskStatus">
                <form action="/changeTaskStatus" method="get">
                    <input type="hidden" name="toDoId" value="<%=toDo.getId()%>">
                    <select name="status">
                        <option value="TODO">TODO</option>
                        <option value="FINISHED">FINISHED</option>
                        <option value="IN_PROGRESS">IN_PROGRESS</option>
                    </select> <input type="submit" value="Update">
                </form>
            </div>
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
