package servlet;

import manager.ToDoManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeTaskImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

public class ChangeToDoImageServlet extends HttpServlet {

    private final String UPLOAD_DIR = "C:\\Users\\Arzuman\\Desktop\\Folder\\web\\TaskManagement\\upload";
    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int toDoId = Integer.parseInt(req.getParameter("toDoId"));
        User user = (User) req.getSession().getAttribute("user");
        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
                toDoManager.updateImage(toDoId, fileName);
            }
        }
        if (user.getUserType() == UserType.MANAGER) {
            req.getRequestDispatcher("/manager").forward(req, resp);
        } else {
            req.getRequestDispatcher("/user").forward(req, resp);
        }


    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

}
