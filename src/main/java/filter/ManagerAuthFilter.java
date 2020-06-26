package filter;

import model.User;
import model.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/manager", "/register", "/task"})
public class ManagerAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserType() != UserType.MANAGER) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/index.jsp");
        }else {
            filterChain.doFilter(servletRequest, servletResponse);

        }
    }


}
