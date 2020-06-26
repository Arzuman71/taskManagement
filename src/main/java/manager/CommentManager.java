package manager;

import db.DBConnectionProvider;
import model.Comment;
import myUtil.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private ToDoManager toDoManager = new ToDoManager();
    private UserManager userManager = new UserManager();

    public void addComment(Comment comment) {
        String sql = "INSERT INTO comment(todo_id,user_id,comment_text) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, comment.getToDo().getId());
            preparedStatement.setLong(2, comment.getUser().getId());
            preparedStatement.setString(3, comment.getCommentText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteComment(Long commentId) {
        String sql = "DELETE FROM `comment` WHERE `id` = " + commentId ;
        try {
           Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Comment> allCommentsByToDoId(long toDoId) {
        String sql = "SELECT * FROM `comment` WHERE todo_id=" + toDoId;
        List<Comment> comments = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                comments.add(getTodoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    private Comment getTodoFromResultSet(ResultSet resultSet) {
        Comment comment = null;

        try {
            comment = Comment.builder()
                    .id(resultSet.getLong(1))
                    .toDo(toDoManager.getToDoById(resultSet.getLong(2)))
                    .user(userManager.getById(resultSet.getLong(3)))
                    .commentText(resultSet.getString(4))
                    .createdDate(DateUtil.convertStringToDate(resultSet.getString(5)))
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }
}
