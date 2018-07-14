package com.aexsharing.web.model;


import com.aexsharing.core.base.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/

@Entity
@Table(name = "technology")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyModel extends BaseModel {

    @Column(name = "Name")
    private String name;

    @Column(name = "Url")
    private String url;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "ImgUrl")
    private String imgUrl;

}
