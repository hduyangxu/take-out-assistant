package cn.edu.zucc.personplan.model;

public class BeanFullReduction {
    private int fullReduction_id;
    private int merchant_id;
    private int fullReduction_request;
    private int FullReduction_money;
    private int FullReduction_isConflict;

    public int getFullReduction_id() {
        return fullReduction_id;
    }

    public void setFullReduction_id(int fullReduction_id) {
        this.fullReduction_id = fullReduction_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getFullReduction_request() {
        return fullReduction_request;
    }

    public void setFullReduction_request(int fullReduction_request) {
        this.fullReduction_request = fullReduction_request;
    }

    public int getFullReduction_money() {
        return FullReduction_money;
    }

    public void setFullReduction_money(int getFullReduction_money) {
        this.FullReduction_money = FullReduction_money;
    }

    public int getFullReduction_isConflict() {
        return FullReduction_isConflict;
    }

    public void setFullReduction_isConflict(int getFullReduction_isConflict) {
        this.FullReduction_isConflict = FullReduction_isConflict;
    }
}
