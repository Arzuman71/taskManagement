<%@ page import="model.ToDo" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="myUtil.DateUtil" %>
<%@ page import="model.User" %>
<%@ page import="model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comment</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">

</head>
<body class="body_2">

<%
    User user = (User) session.getAttribute("user");

    ToDo toDo = (ToDo) request.getAttribute("ToDoById");
    List<Comment> comments = (List<Comment>) request.getAttribute("commentsByToDoId");
    if (user.getUserType() == UserType.MANAGER) {
%>
<a href="/manager">HOME</a>
<%} else {%>
<a href="/user">HOME</a>
<%}%>
<div class="control2">

    <div class="for_comment_photo"><% if (toDo.getPictureUrl() != null) { %>
        <img src="/image?path=<%=toDo.getPictureUrl()%>" width="50"/>

        <%} else {%>
        <img class="todo_photo" src="/image/na.jpg"/>
        <%}%>
    </div>
    <h1>
        <%=toDo.getName()%>
        <%=toDo.getStatus()%><br>
    </h1>
    <h2 class="for_h2"><%=DateUtil.convertDateToString(toDo.getCreatedDate())%>---
        <%=DateUtil.convertDateToString(toDo.getDeadline())%>
    </h2>
    <h3><%=toDo.getDescription()%>
    </h3>

    <div class="comment_block">
        <h2>comments</h2>
        <%
            if (comments != null || comments.size() == 0) {
                for (Comment comment : comments) {
        %>
        <%=comment.getUser().getName()%><br>
        <%=DateUtil.convertDateToString(comment.getCreatedDate())%>
        <p style=" text-align: justify"><%=comment.getCommentText()%>
        </p>
        <%
            if (user.getUserType()==UserType.MANAGER || user.equals(comment.getUser())) {%>
        <form action="/removeComment" method="get">
            <input type="hidden" name="commentId" value="<%=comment.getId()%>">
            <input type="hidden" name="toDoId" value="<%=toDo.getId()%>">
            <input type="submit" value="REMOVE">
        </form>

        <%
                    }
                }
            }
        %>
        <form action="/addComments" method="post">
            <input type="hidden" name="toDoId" value="<%=toDo.getId()%>">
            <input name="commentText" type="text"/><br>
            <input type="submit" value="comment">
        </form>
    </div>
</div>
</body>
</html>
