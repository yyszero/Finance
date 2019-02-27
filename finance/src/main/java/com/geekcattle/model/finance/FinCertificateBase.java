package com.geekcattle.model.finance;

import com.geekcattle.model.BaseEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Table(name = "fin_certificate_base")
public class FinCertificateBase extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 记账日期
     */
    @Column(name = "bookkeeping_time")
    private Date bookkeepingTime;

    @Transient
    private String bookkeepingTimeStr;


    /**
     * 出纳
     */
    private String cashier;

    /**
     * 审核
     */
    private String review;

    /**
     * 制单
     */
    private String orders;

    @Column(name = "create_time")
    private Date createTime;
    @Transient
    private String createTimeStr;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /*凭证类型*/
    @Transient
    private List<FinCertificateItem> finCertificateItems;


    public List<FinCertificateItem> getFinCertificateItems() {
        return finCertificateItems;
    }

    public void setFinCertificateItems(List<FinCertificateItem> finCertificateItems) {
        this.finCertificateItems = finCertificateItems;
    }

    private  String type;

    private int number;

    public String getBookkeepingTimeStr() {
        return bookkeepingTimeStr;
    }

    public void setBookkeepingTimeStr(String bookkeepingTimeStr) {
        this.bookkeepingTimeStr = bookkeepingTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
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
     * 获取记账日期
     *
     * @return bookkeeping_time - 记账日期
     */
    public Date getBookkeepingTime() {
        return bookkeepingTime;
    }

    /**
     * 设置记账日期
     *
     * @param bookkeepingTime 记账日期
     */
    public void setBookkeepingTime(Date bookkeepingTime) {
        this.bookkeepingTime = bookkeepingTime;

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        this.bookkeepingTimeStr = sf.format(bookkeepingTime);
    }

    /**
     * 获取出纳
     *
     * @return cashier - 出纳
     */
    public String getCashier() {
        return cashier;
    }

    /**
     * 设置出纳
     *
     * @param cashier 出纳
     */
    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    /**
     * 获取审核
     *
     * @return review - 审核
     */
    public String getReview() {
        return review;
    }

    /**
     * 设置审核
     *
     * @param review 审核
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * 获取制单
     *
     * @return orders - 制单
     */
    public String getOrders() {
        return orders;
    }

    /**
     * 设置制单
     *
     * @param orders 制单
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTimeStr = sf.format(createTime);

    }

    /**
     * @return create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return update_user
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}