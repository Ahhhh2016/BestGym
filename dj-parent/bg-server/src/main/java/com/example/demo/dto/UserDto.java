package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *  userDto
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserDto", description = "用户请求/返回结果")
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 员工id
     */
    private String companyId;

    /**
     * ids
     */
    private String ids;


    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 旧密码
     */
    @ApiModelProperty(value = "旧密码", hidden = true)
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", hidden = true)
    private String newPassword;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户手机号
     */
    private String phoneNumber;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private Date born;

    /**
     * 用户性别（0：男，1：女，2：未知）
     */
    private Integer sex;

    /**
     * 是否为admin账号 （0：是，1：否）
     */
    private Boolean isAdmin = false;

    /**
     * 公司类型 默认0 货代, 1 工厂
     */
    @ApiModelProperty(value = "公司类型")
    private Integer accountType;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 状态（0：启用，1：禁用）
     */
    private Integer status;

    /**
     * 头像id
     */
    @ApiModelProperty(value = "头像id", hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String avatarId;

    /**
     * 创建日期
     */
    protected Date createDate;

    /**
     * 最后登录时间
     */
    private Date loginDate;

}
