package com.aexsharing.api.todo;

import com.aexsharing.api.user.User;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Todo是一个领域对象（domain object）
 * Created by Kevin Xu on 2018/06/25.
 */
@Data
public class Todo {
    @Id private String id;
    private String desc;
    private boolean completed;
    private User user;
}
