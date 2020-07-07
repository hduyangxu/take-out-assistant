package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanDiscountCoupon {
    private int discountCoupon_id;
    private int merchant_id;
    private float discountCoupon_money;
    private Date discountCoupon_startDate;
    private Date discountCoupon_endDate;
    private int discountCoupon_request;
    public static final String[] tableTitles={"优惠金额","起始日期","截止日期","需要订单数"};

    public String getCell(int col){
        if(col==0) return String.valueOf(this.discountCoupon_money);
        else if(col==1) return String.valueOf(this.discountCoupon_startDate);
        else if(col==2) return String.valueOf(this.discountCoupon_endDate);
        else if(col==3) return String.valueOf(this.discountCoupon_request);
        else return "";
    }

    public int getDiscountCoupon_id() {
        return discountCoupon_id;
    }

    public void setDiscountCoupon_id(int discountCoupon_id) {
        this.discountCoupon_id = discountCoupon_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public float getDiscountCoupon_money() {
        return discountCoupon_money;
    }

    public void setDiscountCoupon_money(float discountCoupon_money) {
        this.discountCoupon_money = discountCoupon_money;
    }

    public Date getDiscountCoupon_startDate() {
        return discountCoupon_startDate;
    }

    public void setDiscountCoupon_startDate(Date discountCoupon_startDate) {
        this.discountCoupon_startDate = discountCoupon_startDate;
    }

    public Date getDiscountCoupon_endDate() {
        return discountCoupon_endDate;
    }

    public void setDiscountCoupon_endDate(Date discountCoupon_endDate) {
        this.discountCoupon_endDate = discountCoupon_endDate;
    }

    public int getDiscountCoupon_request() {
        return discountCoupon_request;
    }

    public void setDiscountCoupon_request(int discountCoupon_request) {
        this.discountCoupon_request = discountCoupon_request;
    }

}
