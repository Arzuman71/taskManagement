package servlet;

import manager.ToDoManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/removeToDo")
public class RemoveToDoServlet extends HttpServlet {

    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long todoId = Long.parseLong(req.getParameter("todoId"));
        toDoManager.deleteToDo(todoId);
        req.getRequestDispatcher("/manager").forward(req, resp);

    }

}
