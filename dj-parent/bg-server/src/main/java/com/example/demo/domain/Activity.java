package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Activity 对象", description = "")
public class Activity extends Model<Activity> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "")
    private String activityName;

    @ApiModelProperty(value = "facilityId")
    private Integer facilityId;

    @ApiModelProperty(value = "")
    private Integer capacity;

    @ApiModelProperty(value = "")
    private Float price;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
