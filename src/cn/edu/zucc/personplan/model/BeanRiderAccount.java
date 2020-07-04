package cn.edu.zucc.personplan.model;

public class BeanRiderAccount {
    private int account_id;
    private int rider_id;
    private int order_id;
    private String order_userEvaluate;
    private float unitPrice;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getRider_id() {
        return rider_id;
    }

    public void setRider_id(int rider_id) {
        this.rider_id = rider_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_userEvaluate() {
        return order_userEvaluate;
    }

    public void setOrder_userEvaluate(String order_userEvaluate) {
        this.order_userEvaluate = order_userEvaluate;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
