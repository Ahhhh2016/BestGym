package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 运动项目
 * </p>
 *
 * @author
 * @since 2022-04-16
 */
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ActivityOrder对象", description="运动项目")
public class ActivityOrder extends Model<ActivityOrder> implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("facilityId")
    private Integer facilityId;

    @TableField("activityId")
    private Integer activityid;

    @TableField("userId")
    private Integer userid;

    @TableField("starttime")
    private Date starttime;

    @TableField("endtime")
    private Date endtime;

    @TableField("period")
    private String period;

    @TableField("paymentStatus")
    private Integer paymentstatus;

    @TableField("paymentMoney")
    private Float paymentMoney;

    @TableField("usageStatus")
    private Integer usagestatus;

    @TableField("QRcode")
    private String qrcode;

    @TableField("useremail")
    private String useremail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getStarttime() {
        return starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setStarttime(Date time) {
        this.starttime = time;
    }

    public void setEndtime(Date time) {
        this.endtime = time;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(Integer paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public Float getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(Float paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public Integer getUsagestatus() {
        return usagestatus;
    }

    public void setUsagestatus(Integer usagestatus) {
        this.usagestatus = usagestatus;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Float getPaymentmoney() {
        return paymentmoney;
    }

    public void setPaymentmoney(Float paymentmoney) {
        this.paymentmoney = paymentmoney;
    }

    public Date getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getFacilitytype() {
        return facilitytype;
    }

    public void setFacilitytype(String facilitytype) {
        this.facilitytype = facilitytype;
    }

    public String getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(String activitytype) {
        this.activitytype = activitytype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public  String getUseremail() { return useremail; }

    public void setUseremail(String useremail) { this.useremail = useremail; }

    @TableField("paymentMoney")
    private Float paymentmoney;

    @TableField("paymentDate")
    private Date paymentdate;

    @TableField("facilityType")
    private String facilitytype;

    @TableField("activityType")
    private String activitytype;

    @TableField("username")
    private String username;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
