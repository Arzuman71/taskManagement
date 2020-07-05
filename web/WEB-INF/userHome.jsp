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


<div id="todolist">
    Loading...
</div>
<script src="/js/jquery.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {

        $("#addTodo").submit(function (e) {
            e.preventDefault();
            let title = $("#title").val();
            let deadline = $("#deadline").val();
            $.ajax({
                url: "/addTodo?title=" + title + "&deadline=" + deadline,
                method: "POST",
                success: function (result) {
                    $("#info").html(result);
                    $("#title").val("");
                    $("#deadline").val("")
                },
                error: function () {
                    $("#info").html("there is problem with todo data.!");
                }

            });

        })

        let getTodoList = function () {
            $.ajax({
                url: "/allTodoList",
                method: "GET",
                success: function (result) {
                    $("#todolist").html(result);
                }

            });
        };
        getTodoList();
        setInterval(getTodoList, 2000)

    })
</script>
</body>
</html>
