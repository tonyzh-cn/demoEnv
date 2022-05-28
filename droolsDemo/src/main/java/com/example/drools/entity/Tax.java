package com.example.drools.entity;

public class Tax {
    private double preTax;//税前工资
    private double taxableIncome;//应纳税所得额
    private double rate;//税率
    private double quick;//速算扣除数
    private double deduction;//扣税额
    private double afterTax;//税后工资

    public double getPreTax() {
        return preTax;
    }

    public void setPreTax(double preTax) {
        this.preTax = preTax;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getQuick() {
        return quick;
    }

    public void setQuick(double quick) {
        this.quick = quick;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(double afterTax) {
        this.afterTax = afterTax;
    }
}
