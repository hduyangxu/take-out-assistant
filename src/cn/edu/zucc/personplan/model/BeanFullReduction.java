package cn.edu.zucc.personplan.model;

public class BeanFullReduction {
    public static final String[] tableTitles={"满减力度"};
    private int fullReduction_id;
    private int merchant_id;
    private float fullReduction_request;
    private float FullReduction_money;

    public String getCell(int col){
        if(col==0) return "满"+this.fullReduction_request+"减"+this.FullReduction_money;
        else return "";
    }

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

    public float getFullReduction_request() {
        return fullReduction_request;
    }

    public void setFullReduction_request(float fullReduction_request) {
        this.fullReduction_request = fullReduction_request;
    }

    public float getFullReduction_money() {
        return FullReduction_money;
    }

    public void setFullReduction_money(float fullReduction_money) {
        FullReduction_money = fullReduction_money;
    }

}
