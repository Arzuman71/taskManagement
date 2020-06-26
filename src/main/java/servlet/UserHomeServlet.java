package servlet;

import manager.ToDoManager;
import model.ToDo;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/user")
public class UserHomeServlet extends HttpServlet {

    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<ToDo> toDoList = toDoManager.getAllToDosByUser(user.getId());
        req.setAttribute("toDoList", toDoList);


        req.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(req, resp);
    }
}
