package servlet;

import manager.CommentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/removeComment")
public class RemoveCommentServlet extends HttpServlet {

    CommentManager commentManager = new CommentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long commentId = Long.parseLong(req.getParameter("commentId"));
        commentManager.deleteComment(commentId);
        req.getRequestDispatcher("/commentHome").forward(req,resp);

    }

}
