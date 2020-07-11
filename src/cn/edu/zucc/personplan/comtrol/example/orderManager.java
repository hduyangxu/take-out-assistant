package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IOrderManager;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;

public class orderManager implements IOrderManager {
    public void addOrder(BeanMerchant merchant, int addressId, int discountCouponId, int fullReductionId, float sumPrice,
                         float finalPrice)throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="insert into tbl_productOrder (address_id,merchant_id,discountCoupon_id," +
                    "fullReduction_id,originalPrice,finalPrice,order_startDate,order_requestDate,order_state,user_id)" +
                    " values (?,?,?,?,?,?,now(),date_add(now(),interval  2 hour),'等待分配骑手',?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,addressId);
            pst.setInt(2,merchant.getMerchant_id());
            pst.setInt(3,discountCouponId);
            pst.setInt(4,fullReductionId);
            pst.setFloat(5,sumPrice);
            pst.setFloat(6,finalPrice);
            pst.setInt(7, BeanUser.currentLoginUser.getUser_id());
            pst.execute();
            int maxOrderId=0;
            sql="select max(order_id) from tbl_productOrder";
            pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) maxOrderId=rs.getInt(1);
            sql="update tbl_orderDetail set order_id = order_id+1 where merchant_id<>? and order_id>=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            pst.setInt(2,maxOrderId);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
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
    }
}
