package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.util.DBUtil2;

import java.sql.Connection;
import java.sql.SQLException;

public class BeanSearchProduct {
    private int product_id;
    private int merchant_id;
    private int type_id;
    private String product_name;
    private float product_price;
    private float product_discountPrice;
    private int product_count;
    private int order_count;
    public static final String[] tableTitles={"商家名","产品类别","产品名","原价","会员价","库存"};

    public String getCell(int col){
        if(col==0) return getMerchant(this.merchant_id);
        else if(col==1) return getType(type_id);
        else if(col==2) return product_name;
        else if(col==3) return String.valueOf(this.product_price);
        else if(col==4) return String.valueOf(this.product_discountPrice);
        else if(col==5) return String.valueOf(this.product_count);
        else return "";
    }

    public String getMerchant(int merchantId){
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select merchant_name from tbl_merchant where merchant_id=" + merchantId;
            java.sql.Statement st=conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if(rs.next())  return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return "";
    }

    public String getType(int TypeId){
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select type_name from tbl_productType where type_id=" + TypeId;
            java.sql.Statement st=conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if(rs.next())  return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return "";
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
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
