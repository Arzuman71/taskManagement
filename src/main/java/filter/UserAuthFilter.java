package filter;

import model.User;
import model.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user","/changeTaskStatus"})
public class UserAuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserType() != UserType.USER) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/");
        }else {
            filterChain.doFilter(servletRequest, servletResponse);

        }
    }

}
