package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanProductOrder {
    private int order_id;
    private int address_id;
    private int merchant_id;
    private int rider_id;
    private int discountCoupon_id;
    private int fullReduction_id;
    private float originalPrice;
    private float finalPrice;
    private Date order_startDate;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getRider_id() {
        return rider_id;
    }

    public void setRider_id(int rider_id) {
        this.rider_id = rider_id;
    }

    public int getDiscountCoupon_id() {
        return discountCoupon_id;
    }

    public void setDiscountCoupon_id(int discountCoupon_id) {
        this.discountCoupon_id = discountCoupon_id;
    }

    public int getFullReduction_id() {
        return fullReduction_id;
    }

    public void setFullReduction_id(int fullReduction_id) {
        this.fullReduction_id = fullReduction_id;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getOrder_startDate() {
        return order_startDate;
    }

    public void setOrder_startDate(Date order_startDate) {
        this.order_startDate = order_startDate;
    }

    public Date getOrder_requestDate() {
        return order_requestDate;
    }

    public void setOrder_requestDate(Date order_requestDate) {
        this.order_requestDate = order_requestDate;
    }

    public Date getOrder_realDate() {
        return order_realDate;
    }

    public void setOrder_realDate(Date order_realDate) {
        this.order_realDate = order_realDate;
    }

    public int getOrder_addressId() {
        return order_addressId;
    }

    public void setOrder_addressId(int order_addressId) {
        this.order_addressId = order_addressId;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    private Date order_requestDate;
    private Date order_realDate;
    private int order_addressId;
    private String order_state;
}
