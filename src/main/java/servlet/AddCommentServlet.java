package servlet;

import manager.CommentManager;
import manager.ToDoManager;
import model.Comment;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addComments")
public class AddCommentServlet extends HttpServlet {

    CommentManager commentManager = new CommentManager();
    ToDoManager toDoManager = new ToDoManager();
    User user = new User();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commentText = req.getParameter("commentText");
        long toDoId = Long.parseLong(req.getParameter("toDoId"));
        user = (User) req.getSession().getAttribute("user");
        Comment comment = Comment.builder()
                .toDo(toDoManager.getToDoById(toDoId))
                .user(user)
                .commentText(commentText)
                .build();
        commentManager.addComment(comment);
        req.getRequestDispatcher("/commentHome").forward(req, resp);
    }
}
