package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;




public class BeanProductOrder {
    private int order_order;
    private int order_id;
    private int address_id;
    private int merchant_id;
    private int rider_id;
    private int discountCoupon_id;
    private int fullReduction_id;
    private float originalPrice;
    private float finalPrice;
    private Date order_startDate;
    private Date order_requestDate;
    private Date order_realDate;
    private int order_addressId;
    private String order_state;
    public static final String[] tableTitles={"序号","地址","商家名","骑手名","优惠券信息","满减信息","原价","最终价","下单时间","预计送达时间","实际送达时间","订单状态"};

    public String getAddress(int addressId){
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select address_detail from tbl_address where address_id=" + addressId;
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
    public String getDiscountCoupon(int discountCouponId){
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select discountCoupon_money from tbl_discountCoupon where discountCoupon_id=" + discountCouponId;
            java.sql.Statement st=conn.createStatement();
            java.sql.ResultSet rs=st.executeQuery(sql);
            if(rs.next())  return String.valueOf(rs.getFloat(1));
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
        return "无";
    }
    public String getRider(int riderId){
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select rider_name from tbl_rider where rider_id=" + riderId;
            java.sql.Statement st=conn.createStatement();
            java.sql.ResultSet rs=st.executeQuery(sql);
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
        return "无";
    }
    public String getFullReduction(int fullReduction_id){
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select fullReduction_request,fullReduction_money from tbl_fullReduction where fullReduction_id=" + fullReduction_id;
            java.sql.Statement st=conn.createStatement();
            java.sql.ResultSet rs=st.executeQuery(sql);
            if(rs.next())  return "满" +rs.getString(1)+"减"+rs.getString(2);
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
        return "无";
    }
    public String getCell(int col){
        if(col==0) return String.valueOf(this.order_order);
        else if(col==1) return getAddress(this.address_id);
        else if(col==2) return getMerchant(this.merchant_id);
        else if(col==3) return getRider(this.rider_id);
        else if(col==4) return getDiscountCoupon(this.discountCoupon_id);
        else if(col==5) return getFullReduction(this.fullReduction_id);
        else if(col==6) return String.valueOf(this.originalPrice);
        else if(col==7) return String.valueOf(this.finalPrice);
        else if(col==8) return String.valueOf(this.order_startDate);
        else if(col==9) return String.valueOf(this.order_requestDate);
        else if(col==10) return String.valueOf(this.order_realDate);
        else if(col==11) return String.valueOf(this.order_state);
        else return "";
    }

    public int getOrder_order() {
        return order_order;
    }

    public void setOrder_order(int order_order) {
        this.order_order = order_order;
    }

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


}
