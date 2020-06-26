<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
    <meta charset="UTF-8mp4">
    <link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
<body class="index_body">


<h1>Sign In Form</h1>
<div id="wrapper">
    <form id="signin" method="post" action="/login" autocomplete="off">
        <input type="text" id="user" name="email" placeholder="email"/>
        <input type="password" id="pass" name="password" placeholder="password"/>
        <button type="submit">&#10145;</button>
        <% if (request.getAttribute("message") != null) {%>
        <div class="login">
            <p style="color: red"><%=request.getAttribute("message")%>
            </p>
                <%}%>

    </form>
</div>
</div>
</body>
</html>

</body>
</html>
