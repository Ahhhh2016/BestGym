package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Facility extends Model<Facility> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String facilityType;

    private Integer activityId;

    private Integer maxCapacity;

    private Integer timetableId;

    private Integer numFacility;
    private String image1Address;
    private String image2Address;
    private String image3Address;

    private String description;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
