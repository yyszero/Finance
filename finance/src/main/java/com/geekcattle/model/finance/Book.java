package com.geekcattle.model.finance;

import com.geekcattle.model.BaseEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bookkeeping_time")
    private Date bookkeepingTime;

    private String summary;

    private Double income;

    private Double pay;

    private Double over;

    private String certificate;

    private String note;
    @Transient
    private String bookkeepingTimeStr;


    public String getBookkeepingTimeStr() {
        return bookkeepingTimeStr;
    }

    public void setBookkeepingTimeStr(String bookkeepingTimeStr) {
        this.bookkeepingTimeStr = bookkeepingTimeStr;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return bookkeeping_time
     */
    public Date getBookkeepingTime() {
        return bookkeepingTime;
    }

    /**
     * @param bookkeepingTime
     */
    public void setBookkeepingTime(Date bookkeepingTime) {
        this.bookkeepingTime = bookkeepingTime;

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        this.bookkeepingTimeStr = sf.format(bookkeepingTime);
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return income
     */
    public Double getIncome() {
        return income;
    }

    /**
     * @param income
     */
    public void setIncome(Double income) {
        this.income = income;
    }

    /**
     * @return pay
     */
    public Double getPay() {
        return pay;
    }

    /**
     * @param pay
     */
    public void setPay(Double pay) {
        this.pay = pay;
    }

    /**
     * @return over
     */
    public Double getOver() {
        return over;
    }

    /**
     * @param over
     */
    public void setOver(Double over) {
        this.over = over;
    }

    /**
     * @return certificate
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * @param certificate
     */
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }
}