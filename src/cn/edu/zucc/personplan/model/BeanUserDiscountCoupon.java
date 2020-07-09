package cn.edu.zucc.personplan.model;

public class BeanUserDiscountCoupon {
    private int user_id;
    private int discountCoupon_id;
    private int discountCoupon_count;
    private String merchant_name;
    private float discountCoupon_money;
    private String discountCoupon_isConflict;
    public static final String[] tableTitles={"商家名称","优惠金额","拥有数量","是否冲突"};

    public String getCell(int col){
        if(col==0) return String.valueOf(this.merchant_name);
        else if(col==1) return String.valueOf(this.discountCoupon_money);
        else if(col==2) return String.valueOf(this.discountCoupon_count);
        else if(col==3) return discountCoupon_isConflict;
        else return "";
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
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
