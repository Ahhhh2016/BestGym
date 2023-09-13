package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * 鐢ㄦ埛
 * </p>
 *
 * @author 
 * @since 2022-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AdminLogin对象", description = "")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "username", required = true)
    private String username;

    @ApiModelProperty(value = "password", required = true)
    private String password;

    @ApiModelProperty(value = "code", required = true)
    private String code;

    @ApiModelProperty(value = "membership_type")
    private Integer membership;

    @ApiModelProperty(value = "cardNumber")
    private Integer cardNumber;

    @ApiModelProperty(value = "profilePhoto_address")
    private String profilePhoto;

    @ApiModelProperty(value = "activityOrderId")
    private Integer activityOrderId;

    @ApiModelProperty(value = "email")
    private String email;

    @TableField(value = "phoneNumber")
    @ApiModelProperty(value = "phoneNumber")
    private String phoneNumber;

    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
