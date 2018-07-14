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
@Table(name = "user")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends BaseModel implements Serializable {

    @Column(name = "Email")
    private String email;

    @Column(name = "NickName")
    private String nickName;

    @Column(name = "Password")
    private String password;

    @Column(name = "Salt")
    private String salt;

    @Column(name = "Gender")
    private int gender = 0;

    @Column(name = "Phone")
    private Long phone = 0L;

    @Column(name = "ValidateCode")
    private String validateCode = "";

    @Column(name = "ImgUrl")
    private String imgUrl = "";

    @Column(name = "Address")
    private String address = "";

    @Column(name = "RegisterTime")
    private Long registerTime = 0L;


}
