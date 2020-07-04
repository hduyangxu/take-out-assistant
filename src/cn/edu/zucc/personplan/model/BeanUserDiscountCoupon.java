package cn.edu.zucc.personplan.model;

public class BeanUserDiscountCoupon {
    private int user_id;
    private int discountCoupon_id;
    private int discountCoupon_count;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDiscountCoupon_id() {
        return discountCoupon_id;
    }

    public void setDiscountCoupon_id(int discountCoupon_id) {
        this.discountCoupon_id = discountCoupon_id;
    }

    public int getDiscountCoupon_count() {
        return discountCoupon_count;
    }

    public void setDiscountCoupon_count(int discountCoupon_count) {
        this.discountCoupon_count = discountCoupon_count;
    }
}
