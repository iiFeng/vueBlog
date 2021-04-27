package com.example.markerhub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotBlank(message = "摘要不能为空")
    private String description;
    @NotBlank(message = "内容不能为空")
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime created;
    private Integer status;

}
