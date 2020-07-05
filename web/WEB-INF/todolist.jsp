<%@ page import="model.ToDo" %>
<%@ page import="java.util.List" %>
<%@ page import="myUtil.DateUtil" %>
<%@ page import="model.User" %>
<%@ page import="model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) request.getSession().getAttribute("user");
    List<ToDo> toDoList = (List<ToDo>) request.getAttribute("toDoList");

    int count = 0;
    for (ToDo toDo : toDoList) {
        count++;
%>
<%if (count % 2 == 0) {%>
<div class="left_todo2">
    <ul><a class="todo_name" href="/commentHome?toDoId=<%=toDo.getId()%>/comments?toDoId=<%=toDo.getId()%>"><%=toDo.getName()%>
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
                    <%if (user.getUserType() == UserType.MANAGER) {%>
                    <a class="delete" href="/removeToDo?todoId=<%= toDo.getId() %>">delete</a>
                    <%}%>
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
                <img class="todo_photo" src="/image?path=<%=toDo.getPictureUrl()%>"/> <%} else {%>
                <img class="todo_photo" src="/image/na.jpg"/>
                    <%}%>

                <div class="changeTaskImage">
                    <form action="/changeTaskImage" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="toDoId" value="<%=toDo.getId()%>">
                        <input name="pictureUrl" type="file"/><br>
                        <input type="submit" value="change photo"/>
                    </form>
                    <%if (user.getUserType() == UserType.MANAGER) {%>
                    <a class="delete" href="/removeToDo?todoId=<%= toDo.getId() %>">delete</a>
                    <%}%>
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