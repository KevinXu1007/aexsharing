package com.aexsharing.core.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Kevin Xu
 * Date: 2018/6/8
 */

@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "UpdateTime")
    private Date updateTime;
}
