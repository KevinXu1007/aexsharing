package com.aexsharing.web.model;


import com.aexsharing.core.base.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
@Entity
@Table(name = "changeLog")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLogModel extends BaseModel implements Serializable {

    @Column(name = "Name")
    private String name;

    @Column(name = "OnlineTime")
    private String onlineTime;
}
