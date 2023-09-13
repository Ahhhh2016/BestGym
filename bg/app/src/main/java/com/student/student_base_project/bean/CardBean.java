package com.student.student_base_project.bean;

public class CardBean {
    private long id;
    private String cardNo;
    private String cardPwd;

    public CardBean(long id, String cardNo, String cardPwd) {
        this.id = id;
        this.cardNo = cardNo;
        this.cardPwd = cardPwd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardPwd() {
        return cardPwd;
    }

    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }
}
