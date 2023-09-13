package com.student.student_base_project.bean;

import java.io.Serializable;

public class CardInfoBean implements Serializable {
    private long id;
    private String cardType;
    private String type ;
    private String price ;
    private int cover;
    private String cishu;
    private String description;
    private int payType;//支付方式 1.cards 2.cash


    public CardInfoBean(long id, String cardType, String type, String price, int cover, String cishu, String description,int payType) {
        this.id = id;
        this.cardType = cardType;
        this.type = type;
        this.price = price;
        this.cover = cover;
        this.cishu = cishu;
        this.description = description;
        this.payType = payType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getCishu() {
        return cishu;
    }

    public void setCishu(String cishu) {
        this.cishu = cishu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
