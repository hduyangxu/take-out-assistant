package cn.edu.zucc.personplan.model;

public class BeanMerchant {
    public static final String[] tableTitles={"商家编号","商家名","星级","平均消费","总销量"};
    private int merchant_id;
    private String merchant_name;
    private int merchant_starRated;
    private float merchant_avgConsumption;
    private int merchant_totalSales;

    public String getCell(int col){
            if(col==0) return String.valueOf(this.merchant_id);
            else if(col==1) return this.merchant_name;
            else if(col==2) return String.valueOf(this.merchant_starRated);
            else if(col==3) return String.valueOf(this.merchant_avgConsumption);
            else if(col==4) return String.valueOf(this.merchant_totalSales);
            else return "";
    }

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

}
