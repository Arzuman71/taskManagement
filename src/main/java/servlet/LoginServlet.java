package servlet;

import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getByEmailAndPassword(email, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            if (user.getUserType() == UserType.MANAGER) {
                req.getRequestDispatcher("/manager").forward(req, resp);
            } else {
                req.getRequestDispatcher("/user").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Email or password invalid");
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }

}
