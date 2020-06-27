package manager;

import myUtil.DateUtil;
import db.DBConnectionProvider;
import model.ToDo;
import model.ToDoStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ToDoManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public boolean create(ToDo todo) {
        try {
            String sql = "INSERT INTO todo(name,description,deadline,status,user_id,picture_url) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, todo.getName());
            preparedStatement.setString(2, todo.getDescription());
            if (todo.getDeadline() != null) {
                preparedStatement.setString(3, DateUtil.convertDateToString(todo.getDeadline()));
            } else {
                preparedStatement.setString(3, null);
            }

            preparedStatement.setString(4, todo.getStatus().name());
            preparedStatement.setLong(5, todo.getUser().getId());
            preparedStatement.setString(6, todo.getPictureUrl());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                todo.setId(id);
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    private ToDo getTodoFromResultSet(ResultSet resultSet) {
        try {
            return ToDo.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .deadline(resultSet.getString(4) == null ? null : DateUtil.convertStringToDate(resultSet.getString(4)))
                    .createdDate(resultSet.getString(5) == null ? null : DateUtil.convertStringToDate(resultSet.getString(5)))
                    .status(ToDoStatus.valueOf(resultSet.getString(6)))
                    .user(new UserManager().getById(resultSet.getLong(7)))
                    .pictureUrl(resultSet.getString(8))
                    .build();
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean update(long Id, String status) {
        try {
            String sql = "UPDATE `todo` SET `status` = ? WHERE `id` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean updateImage(long Id, String pictureUrl) {
        try {
            String sql = "UPDATE `todo` SET `picture_url` = ? WHERE `id` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pictureUrl);
            preparedStatement.setLong(2, Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }


    public List<ToDo> getAll() {

        List<ToDo> toDos = new LinkedList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM todo");
            while (resultSet.next()) {
                toDos.add(getTodoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return toDos;
    }

    public ToDo getToDoById(long toDoId) {
        ToDo toDo = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM todo WHERE id =" + toDoId);
            if (resultSet.next()) {
                toDo = getTodoFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDo;
    }

    public boolean deleteToDo(Long toDoId) {
        String sql = "DELETE FROM todo WHERE id = " + toDoId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<ToDo> getAllToDosByUser(long userId) {
        List<ToDo> toDos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM todo WHERE user_id=" + userId);
            while (resultSet.next()) {
                toDos.add(getTodoFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDos;
    }
}