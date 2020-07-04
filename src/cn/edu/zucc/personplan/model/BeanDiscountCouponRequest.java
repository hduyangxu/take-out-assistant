package cn.edu.zucc.personplan.model;

public class BeanDiscountCouponRequest {
    private int user_id;
    private int merchant_id;
    private int discountCoupon_id;
    private int discountCoupon_already;
    private int discountCoupon_request;

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
