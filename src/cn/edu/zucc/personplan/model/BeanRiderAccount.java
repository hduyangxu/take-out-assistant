package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanRiderAccount {
    private int account_id;
    private Date finish_date;
    private int account_order;
    private int rider_id;
    private int order_id;
    private String order_userEvaluate;
    private float unitPrice;
    public static final String[] tableTitles={"当月序号","订单时间","用户评价","收入"};

    public String getCell(int col){
        if(col==0) return String.valueOf(this.account_order);
        else if(col==1) return String.valueOf(this.finish_date);
        else if(col==2) return this.order_userEvaluate;
        else if(col==3) return String.valueOf(this.unitPrice);
        else return "";
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public int getAccount_order() {
        return account_order;
    }

    public void setAccount_order(int account_order) {
        this.account_order = account_order;
    }

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
