package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanProductEvaluate {
    private int product_id;
    private int user_id;
    private int merchant_id;
    private String evaluate_content;
    private Date evaluate_date;
    private int evaluate_starRated;
    private String evaluate_photo;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public String getEvaluate_content() {
        return evaluate_content;
    }

    public void setEvaluate_content(String evaluate_content) {
        this.evaluate_content = evaluate_content;
    }

    public Date getEvaluate_date() {
        return evaluate_date;
    }

    public void setEvaluate_date(Date evaluate_date) {
        this.evaluate_date = evaluate_date;
    }

    public int getEvaluate_starRated() {
        return evaluate_starRated;
    }

    public void setEvaluate_starRated(int evaluate_starRated) {
        this.evaluate_starRated = evaluate_starRated;
    }

    public String getEvaluate_photo() {
        return evaluate_photo;
    }

    public void setEvaluate_photo(String evaluate_photo) {
        this.evaluate_photo = evaluate_photo;
    }
}
