package com.geekcattle.model.finance;

import com.geekcattle.model.BaseEntity;

import javax.persistence.*;

@Table(name = "fin_certificate_item")
public class FinCertificateItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 凭证主表id
     */
    @Column(name = "certificate_id")
    private Integer certificateId;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 借方科目
     */
    @Column(name = "debit_account")
    private String debitAccount;

    /**
     * 科目代码
     */
    @Column(name = "debit_account_code")
    private String debitAccountCode;

    /**
     * 贷方科目
     */
    @Column(name = "loan_account")
    private String loanAccount;

    /**
     * 贷方科目代码
     */
    @Column(name = "loan_account_code")
    private String loanAccountCode;

    /**
     * 借方金额
     */
    @Column(name = "debit_amount")
    private Double debitAmount;

    /**
     * 贷方金额
     */
    @Column(name = "loan_amount")
    private Double loanAmount;
    /**
     * 剩余
     */
    private  Double over;

    public Double getOver() {
        return over;
    }

    public void setOver(Double over) {
        this.over = over;
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
     * 获取凭证主表id
     *
     * @return certificate_id - 凭证主表id
     */
    public Integer getCertificateId() {
        return certificateId;
    }

    /**
     * 设置凭证主表id
     *
     * @param certificateId 凭证主表id
     */
    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    /**
     * 获取摘要
     *
     * @return summary - 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取借方科目
     *
     * @return debit_account - 借方科目
     */
    public String getDebitAccount() {
        return debitAccount;
    }

    /**
     * 设置借方科目
     *
     * @param debitAccount 借方科目
     */
    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    /**
     * 获取科目代码
     *
     * @return debit_account_code - 科目代码
     */
    public String getDebitAccountCode() {
        return debitAccountCode;
    }

    /**
     * 设置科目代码
     *
     * @param debitAccountCode 科目代码
     */
    public void setDebitAccountCode(String debitAccountCode) {
        this.debitAccountCode = debitAccountCode;
    }

    /**
     * 获取贷方科目
     *
     * @return loan_account - 贷方科目
     */
    public String getLoanAccount() {
        return loanAccount;
    }

    /**
     * 设置贷方科目
     *
     * @param loanAccount 贷方科目
     */
    public void setLoanAccount(String loanAccount) {
        this.loanAccount = loanAccount;
    }

    /**
     * 获取贷方科目代码
     *
     * @return loan_account_code - 贷方科目代码
     */
    public String getLoanAccountCode() {
        return loanAccountCode;
    }

    /**
     * 设置贷方科目代码
     *
     * @param loanAccountCode 贷方科目代码
     */
    public void setLoanAccountCode(String loanAccountCode) {
        this.loanAccountCode = loanAccountCode;
    }

    /**
     * 获取借方金额
     *
     * @return debit_amount - 借方金额
     */
    public Double getDebitAmount() {
        return debitAmount;
    }

    /**
     * 设置借方金额
     *
     * @param debitAmount 借方金额
     */
    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    /**
     * 获取贷方金额
     *
     * @return loan_amount - 贷方金额
     */
    public Double getLoanAmount() {
        return loanAmount;
    }

    /**
     * 设置贷方金额
     *
     * @param loanAmount 贷方金额
     */
    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }
}