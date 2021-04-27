package com.example.markerhub.entity;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class User implements Serializable {
    private static final long  serialVersionUID = 1L;
    private Long id;
    @NotNull(message = "昵称不能为空")
    private String userName;
    private String avatar;
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    private String password;
    private Integer status;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
}
