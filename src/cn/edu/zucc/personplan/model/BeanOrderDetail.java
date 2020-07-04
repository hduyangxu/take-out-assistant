package cn.edu.zucc.personplan.model;

public class BeanOrderDetail {
    private int product_id;
    private int merchant_id;
    private int order_id;
    private int product_quantity;
    private float product_sumPrice;
    private float product_discountPrice;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public float getProduct_sumPrice() {
        return product_sumPrice;
    }

    public void setProduct_sumPrice(float product_sumPrice) {
        this.product_sumPrice = product_sumPrice;
    }

    public float getProduct_discountPrice() {
        return product_discountPrice;
    }

    public void setProduct_discountPrice(float product_discountPrice) {
        this.product_discountPrice = product_discountPrice;
    }
}
