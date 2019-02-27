package com.geekcattle.model.finance;

import com.geekcattle.model.BaseEntity;

import javax.persistence.*;

public class FinGeneralLedger extends BaseEntity {

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



    private String  totalDate ;


    private double over;


    private  double debitTotal;

    private  double loanTotal;


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getDebitAccountCode() {
        return debitAccountCode;
    }

    public void setDebitAccountCode(String debitAccountCode) {
        this.debitAccountCode = debitAccountCode;
    }

    public String getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(String totalDate) {
        this.totalDate = totalDate;
    }

    public double getOver() {
        return over;
    }

    public void setOver(double over) {
        this.over = over;
    }

    public double getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(double debitTotal) {
        this.debitTotal = debitTotal;
    }

    public double getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(double loanTotal) {
        this.loanTotal = loanTotal;
    }
}