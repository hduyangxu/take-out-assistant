package cn.edu.zucc.personplan.model;

public class BeanProductDetails {
    private int product_id;
    private int merchant_id;
    private int type_id;
    private String product_name;
    private float product_price;
    private float product_discountPrice;
    public static final String[] tableTitles={"产品名","产品价格","优惠价格"};

    public String getCell(int col){
        if(col==0) return this.product_name;
        else if(col==1) return String.valueOf(this.product_price);
        else if(col==2) return String.valueOf(this.product_discountPrice);
        else return "";
    }

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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public float getProduct_discountPrice() {
        return product_discountPrice;
    }

    public void setProduct_discountPrice(float product_discountPrice) {
        this.product_discountPrice = product_discountPrice;
    }
}
