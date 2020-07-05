package servlet;

import manager.ToDoManager;
import manager.UserManager;
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

@WebServlet(urlPatterns = "/manager")
public class ManagerHomeServlet extends HttpServlet {
    UserManager userManager = new UserManager();
    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("allUsers", userManager.getAllUsers());
        req.getRequestDispatcher("WEB-INF/managerHome.jsp").forward(req, resp);

    }

}
