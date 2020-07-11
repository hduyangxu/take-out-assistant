package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IOrderManager;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class orderManager implements IOrderManager {
    public void addOrder(BeanMerchant merchant, int addressId, int discountCouponId, int fullReductionId, float sumPrice,
                         float finalPrice)throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            //插入订单信息
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
            //使用优惠券
            sql="update tbl_userDiscountCoupon set discountCoupon_count=discountCoupon_count-1 where " +
                    "user_id=? and discountCoupon_id=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,BeanUser.currentLoginUser.getUser_id());
            pst.setInt(2,discountCouponId);
            pst.execute();
            //删除数量为0的优惠券
            sql="delete from tbl_userDiscountCoupon where discountCoupon_count=0";
            pst=conn.prepareStatement(sql);
            pst.execute();
            //获取当前订单号
            int maxOrderId=0;
            sql="select max(order_id) from tbl_productOrder";
            pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) maxOrderId=rs.getInt(1);
            rs.close();
            //更新购物车信息
            sql="update tbl_orderDetail set order_id = ? where order_id=1";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,maxOrderId);
            pst.execute();
            sql="update tbl_orderDetail set order_id = ? + 1 where merchant_id<>? and order_id>=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,maxOrderId);
            pst.setInt(2,merchant.getMerchant_id());
            pst.setInt(3,maxOrderId);
            pst.execute();
            //更新集单送券信息
            //商家优惠券表中有,集单送券表中没有,插入集单送券表
            sql="select discountCoupon_id, discountCoupon_request,discountCoupon_money,discountCoupon_isConflict from tbl_discountcoupon " +
                    "where merchant_id = ? and discountCoupon_id not in" +
                    "(select discountCoupon_id from tbl_discountcouponrequest)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            rs=pst.executeQuery();
            while(rs.next()){
                sql="INSERT into tbl_discountcouponrequest values(?,?,?,0,?,?,?)";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,BeanUser.currentLoginUser.getUser_id());
                pst.setInt(2,merchant.getMerchant_id());
                pst.setInt(3,rs.getInt(1));
                pst.setInt(4,rs.getInt(2));
                pst.setFloat(5,rs.getFloat(3));
                pst.setString(6,rs.getString(4));
                pst.execute();
            }
            //集单送券表中已有，已有数量+1 == 要求数：删除集单中的对应数据，用户拥有优惠券+1
            sql="select discountCoupon_id FROM tbl_discountcouponrequest where discountCoupon_already+1 = discountCoupon_request " +
                    "and discountCoupon_id in(SELECT discountCoupon_id from tbl_userdiscountcoupon) " +
                    "and merchant_id=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            rs=pst.executeQuery();
            while(rs.next()){
                sql="update tbl_userdiscountcoupon set discountCoupon_count = discountCoupon_count + 1 where discountCoupon_id=?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,rs.getInt(1));
                pst.execute();
                sql="delete from tbl_discountcouponrequest where discountCoupon_id=?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,rs.getInt(1));
                pst.execute();
            }
            sql="select discountCoupon_id,discountCoupon_money,discountCoupon_isConflict FROM tbl_discountcouponrequest where discountCoupon_already+1 = discountCoupon_request " +
                    "and discountCoupon_id not in(SELECT discountCoupon_id from tbl_userdiscountcoupon) " +
                    "and merchant_id=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            rs=pst.executeQuery();
            while(rs.next()){
                sql="INSERT into tbl_userdiscountcoupon VALUES(?,?,1,?,?,?)";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,BeanUser.currentLoginUser.getUser_id());
                pst.setInt(2,rs.getInt(1));
                pst.setString(3,merchant.getMerchant_name());
                pst.setFloat(4,rs.getFloat(2));
                pst.setString(5,rs.getString(3));
                pst.execute();
                sql="delete from tbl_discountcouponrequest where discountCoupon_id=?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,rs.getInt(1));
                pst.execute();
            }
            //集单送券表中已有，已有数量+1 != 要求数,已有数量+1
            sql="SELECT discountCoupon_id FROM tbl_discountcouponrequest " +
                    "WHERE discountCoupon_already + 1 <> discountCoupon_request " +
                    "and merchant_id=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            rs=pst.executeQuery();
            while(rs.next()){
                sql="update tbl_discountcouponrequest set discountCoupon_already = discountCoupon_already + 1 where discountCoupon_id = ?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,rs.getInt(1));
                pst.execute();
            }

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
