package servlet;

import manager.CommentManager;
import manager.ToDoManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/commentHome")
public class CommentHomeServlet extends HttpServlet {

    ToDoManager toDoManager = new ToDoManager();
    CommentManager commentManager = new CommentManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long toDoId = Long.parseLong(req.getParameter("toDoId"));
        req.setAttribute("ToDoById", toDoManager.getToDoById(toDoId));
        req.setAttribute("commentsByToDoId", commentManager.allCommentsByToDoId(toDoId));
        req.getRequestDispatcher("WEB-INF/toDoComment.jsp").forward(req, resp);

    }
}
