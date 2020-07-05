package servlet;

import manager.ToDoManager;
import model.ToDo;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/allTodoList")
public class AllToDoListServlet extends HttpServlet {

    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user.getUserType() == UserType.MANAGER) {

            List<ToDo> toDoList = toDoManager.getAll();
            req.setAttribute("toDoList", toDoList);
        } else {
            List<ToDo> toDoList = toDoManager.getAllToDosByUser(user.getId());
            req.setAttribute("toDoList", toDoList);

        }
        req.getRequestDispatcher("/WEB-INF/todolist.jsp").forward(req, resp);
    }

}
