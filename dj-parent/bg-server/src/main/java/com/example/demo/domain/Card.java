package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 会员卡
 * </p>
 *
 * @author 
 * @since 2022-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Card对象", description="会员卡")
public class Card extends Model<Card> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "number", type = IdType.INPUT)
    private String number;

    private Integer userid;

    private String bankname;


    @Override
    protected Serializable pkVal() {
        return this.number;
    }

}
