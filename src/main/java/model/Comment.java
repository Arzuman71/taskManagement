package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private long id;
    private ToDo toDo;
    private User user;
    private String commentText;
    private Date createdDate;
}
