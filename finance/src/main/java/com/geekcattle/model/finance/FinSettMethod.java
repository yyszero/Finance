package com.geekcattle.model.finance;

import com.geekcattle.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "fin_sett_method")
public class FinSettMethod  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sett_method_name")
    private String settMethodName;

    @Column(name = "sett_method_code")
    private String settMethodCode;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user")
    private String updateUser;

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
     * @return sett_method_name
     */
    public String getSettMethodName() {
        return settMethodName;
    }

    /**
     * @param settMethodName
     */
    public void setSettMethodName(String settMethodName) {
        this.settMethodName = settMethodName;
    }

    /**
     * @return sett_method_code
     */
    public String getSettMethodCode() {
        return settMethodCode;
    }

    /**
     * @param settMethodCode
     */
    public void setSettMethodCode(String settMethodCode) {
        this.settMethodCode = settMethodCode;
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
}