package cn.edu.zucc.personplan.model;

public class BeanProductType {
    private int merchant_id;
    private int type_id;
    private String type_name;
    private int type_quantity;
    public static final String[] tableTitles={"类别名","类别数量"};

    public String getCell(int col){
        if(col==0) return this.type_name;
        else if(col==1) return String.valueOf(this.type_quantity);
        else return "";
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getType_quantity() {
        return type_quantity;
    }

    public void setType_quantity(int type_quantity) {
        this.type_quantity = type_quantity;
    }
}
