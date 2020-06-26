package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDo {
    private long id;
    private String name;
    private String description;
    private Date deadline;
    private Date createdDate;
    private ToDoStatus status;
    private User user;
    private String pictureUrl;
}
