package servlet;

import manager.ToDoManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeTaskStatus")

public class ChangeTaskStatusServlet extends HttpServlet {
    ToDoManager toDoManager = new ToDoManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        int toDoId = Integer.parseInt(req.getParameter("toDoId"));
        String toDoStatus = req.getParameter("status");
        toDoManager.update(toDoId, toDoStatus);
        if (user.getUserType() == UserType.MANAGER) {
            resp.sendRedirect("/manager");
        } else {
            resp.sendRedirect("/user");
        }
    }

}