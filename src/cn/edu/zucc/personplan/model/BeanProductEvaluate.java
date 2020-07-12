package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.util.DBUtil2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class BeanProductEvaluate {
    private int product_id;
    private int user_id;
    private int merchant_id;
    private String evaluate_content;
    private Date evaluate_date;
    private int evaluate_starRated;
    private String evaluate_photo;
    public static final String[] tableTitles={"商家名","产品名","评价内容","评价星级","评价时间"};

    public String getCell(int col){
        if(col==0) return getMerchant(this.merchant_id);
        else if(col==1) return getProduct(this.product_id);
        else if(col==2) return evaluate_content;
        else if(col==3) return String.valueOf(evaluate_starRated);
        else if(col==4) return String.valueOf(evaluate_date);
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

    public String getProduct(int productId){
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select product_name from tbl_productDetails where product_id=" + productId;
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
