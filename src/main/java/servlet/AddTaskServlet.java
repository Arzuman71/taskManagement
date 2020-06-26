package servlet;

import myUtil.DateUtil;
import manager.ToDoManager;
import manager.UserManager;
import model.ToDo;
import model.ToDoStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/task")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddTaskServlet extends HttpServlet {

    ToDo toDo = new ToDo();
    UserManager userManager = new UserManager();
    private final String UPLOAD_DIR = "C:\\Users\\Arzuman\\Desktop\\Folder\\web\\TaskManagement\\upload";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        long userId = Long.parseLong(req.getParameter("userId"));
        Date deadline = null;
        deadline = DateUtil.convertStringToDate(req.getParameter("deadline"));
        toDo = ToDo.builder()
                .name(name)
                .description(description)
                .deadline(deadline)
                .status(ToDoStatus.valueOf(status.toUpperCase()))
                .user(userManager.getById(userId))
                .build();
        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
                toDo.setPictureUrl(fileName);
            }
        }
        new ToDoManager().create(toDo);
        req.getRequestDispatcher("/manager").forward(req, resp);
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

}
