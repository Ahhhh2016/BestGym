package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MembershipOrder extends Model<MembershipOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer membershipType;

    private Float paymentMoney;

    private Integer userId;


    private Date paymentDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
