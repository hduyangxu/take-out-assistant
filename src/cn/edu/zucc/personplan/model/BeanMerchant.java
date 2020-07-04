package cn.edu.zucc.personplan.model;

public class BeanMerchant {
    private int merchant_id;
    private String merchant_name;
    private int merchant_starRated;

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public int getMerchant_starRated() {
        return merchant_starRated;
    }

    public void setMerchant_starRated(int merchant_starRated) {
        this.merchant_starRated = merchant_starRated;
    }

    public float getMerchant_avgConsumption() {
        return merchant_avgConsumption;
    }

    public void setMerchant_avgConsumption(float merchant_avgConsumption) {
        this.merchant_avgConsumption = merchant_avgConsumption;
    }

    public int getMerchant_totalSales() {
        return merchant_totalSales;
    }

    public void setMerchant_totalSales(int merchant_totalSales) {
        this.merchant_totalSales = merchant_totalSales;
    }

    private float merchant_avgConsumption;
    private int merchant_totalSales;
}
