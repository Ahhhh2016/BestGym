package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 职工
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private Integer role;

    private String profilePhoto;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
