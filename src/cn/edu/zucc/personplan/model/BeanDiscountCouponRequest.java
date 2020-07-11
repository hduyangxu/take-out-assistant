package cn.edu.zucc.personplan.model;

public class BeanDiscountCouponRequest {
    private int user_id;
    private int merchant_id;
    private int discountCoupon_id;
    private int discountCoupon_already;
    private int discountCoupon_request;
    private float discountCoupon_money;
    private  String discountCoupon_isConflict;
    public static final String[] tableTitles={"商家编号","优惠券金额","已买单数","需要订单数","是否冲突"};

    public String getCell(int col){
        if(col==0) return String.valueOf(this.discountCoupon_id);
        else if(col==1) return String.valueOf(this.discountCoupon_money);
        else if(col==2) return String.valueOf(this.discountCoupon_already);
        else if(col==3) return String.valueOf(this.discountCoupon_request);
        else if(col==4) return discountCoupon_isConflict;
        else return "";
    }

    public float getDiscountCoupon_money() {
        return discountCoupon_money;
    }

    public void setDiscountCoupon_money(float discountCoupon_money) {
        this.discountCoupon_money = discountCoupon_money;
    }

    public String getDiscountCoupon_isConflict() {
        return discountCoupon_isConflict;
    }

    public void setDiscountCoupon_isConflict(String discountCoupon_isConflict) {
        this.discountCoupon_isConflict = discountCoupon_isConflict;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getDiscountCoupon_id() {
        return discountCoupon_id;
    }

    public void setDiscountCoupon_id(int discountCoupon_id) {
        this.discountCoupon_id = discountCoupon_id;
    }

    public int getDiscountCoupon_already() {
        return discountCoupon_already;
    }

    public void setDiscountCoupon_already(int discountCoupon_already) {
        this.discountCoupon_already = discountCoupon_already;
    }

    public int getDiscountCoupon_request() {
        return discountCoupon_request;
    }

    public void setDiscountCoupon_request(int discountCoupon_request) {
        this.discountCoupon_request = discountCoupon_request;
    }
}
