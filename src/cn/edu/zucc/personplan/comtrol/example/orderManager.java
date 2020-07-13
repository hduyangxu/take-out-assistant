package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IOrderManager;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.model.BeanProductOrder;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                    "(select discountCoupon_id from tbl_discountcouponrequest where user_id = ?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            pst.setInt(2,BeanUser.currentLoginUser.getUser_id());
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
                    "and discountCoupon_id in(SELECT discountCoupon_id from tbl_userdiscountcoupon where user_id = ?) " +
                    "and merchant_id=? " +
                    "and user_id = ?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,BeanUser.currentLoginUser.getUser_id());
            pst.setInt(2,merchant.getMerchant_id());
            pst.setInt(3,BeanUser.currentLoginUser.getUser_id());
            rs=pst.executeQuery();
            while(rs.next()){
                sql="update tbl_userdiscountcoupon set discountCoupon_count = discountCoupon_count + 1 where discountCoupon_id=? and user_id = ?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,rs.getInt(1));
                pst.setInt(2,BeanUser.currentLoginUser.getUser_id());
                pst.execute();
                sql="delete from tbl_discountcouponrequest where discountCoupon_id=? and user_id = ?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,rs.getInt(1));
                pst.setInt(2,BeanUser.currentLoginUser.getUser_id());
                pst.execute();
            }
            sql="select discountCoupon_id,discountCoupon_money,discountCoupon_isConflict FROM tbl_discountcouponrequest where discountCoupon_already+1 = discountCoupon_request " +
                    "and discountCoupon_id not in(SELECT discountCoupon_id from tbl_userdiscountcoupon where user_id = ?) " +
                    "and merchant_id=? and user_id = ?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,BeanUser.currentLoginUser.getUser_id());
            pst.setInt(2,merchant.getMerchant_id());
            pst.setInt(3,BeanUser.currentLoginUser.getUser_id());
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
                sql="delete from tbl_discountcouponrequest where discountCoupon_id=? and user_id = ?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,rs.getInt(1));
                pst.setInt(2,BeanUser.currentLoginUser.getUser_id());
                pst.execute();
            }
            //集单送券表中已有，已有数量+1 != 要求数,已有数量+1
            sql="SELECT discountCoupon_id FROM tbl_discountcouponrequest " +
                    "WHERE discountCoupon_already + 1 <> discountCoupon_request " +
                    "and merchant_id=? and user_id = ?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            pst.setInt(2,BeanUser.currentLoginUser.getUser_id());
            rs=pst.executeQuery();
            while(rs.next()){
                sql="update tbl_discountcouponrequest set discountCoupon_already = discountCoupon_already + 1 where discountCoupon_id = ? and user_id = ?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,rs.getInt(1));
                pst.setInt(2,BeanUser.currentLoginUser.getUser_id());
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

    public void confirmArrive(BeanProductOrder productOrder) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="update tbl_productorder set order_realDate = now(), order_state='已完成' where order_id = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,productOrder.getOrder_id());
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

    public void distributeRider(BeanAllProductOrder productOrder, BeanRider rider) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="update tbl_productorder set rider_id=?,order_state='已送出' where order_id = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,rider.getRider_id());
            pst.setInt(2,productOrder.getOrder_id());
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

    public List<BeanProductOrder> loadProductOrder() throws BaseException{
        List<BeanProductOrder> result=new ArrayList<BeanProductOrder>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_ProductOrder where user_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,BeanUser.currentLoginUser.getUser_id());
            java.sql.ResultSet rs= pst.executeQuery();
            int cnt=1;
            while(rs.next()){
                BeanProductOrder bpo = new BeanProductOrder();
                bpo.setOrder_id(rs.getInt(1));
                bpo.setOrder_order(cnt++);
                bpo.setAddress_id(rs.getInt(2));
                bpo.setMerchant_id(rs.getInt(3));
                bpo.setRider_id(rs.getInt(4));
                bpo.setDiscountCoupon_id(rs.getInt(5));
                bpo.setFullReduction_id(rs.getInt(6));
                bpo.setOriginalPrice(rs.getFloat(7));
                bpo.setFinalPrice(rs.getFloat(8));
                bpo.setOrder_startDate(rs.getTimestamp(9));
                bpo.setOrder_requestDate(rs.getTimestamp(10));
                bpo.setOrder_realDate(rs.getTimestamp(11));
                bpo.setOrder_state(rs.getString(12));
                result.add(bpo);
            }
            return result;
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
    public List<BeanAllProductOrder> loadAllProductOrder() throws BaseException{
        List<BeanAllProductOrder> result=new ArrayList<BeanAllProductOrder>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_ProductOrder";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanAllProductOrder bapo = new BeanAllProductOrder();
                bapo.setOrder_id(rs.getInt(1));
                bapo.setAddress_id(rs.getInt(2));
                bapo.setMerchant_id(rs.getInt(3));
                bapo.setRider_id(rs.getInt(4));
                bapo.setDiscountCoupon_id(rs.getInt(5));
                bapo.setFullReduction_id(rs.getInt(6));
                bapo.setOriginalPrice(rs.getFloat(7));
                bapo.setFinalPrice(rs.getFloat(8));
                bapo.setOrder_startDate(rs.getTimestamp(9));
                bapo.setOrder_requestDate(rs.getTimestamp(10));
                bapo.setOrder_realDate(rs.getTimestamp(11));
                bapo.setOrder_state(rs.getString(12));
                bapo.setUser_id(rs.getInt(13));
                result.add(bapo);
            }
            return result;
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

    public List<BeanOrderDetail> loadOrderDetails(BeanProductOrder productOrder) throws BaseException{
        List<BeanOrderDetail> result=new ArrayList<BeanOrderDetail>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_orderDetail where order_id = ? and user_id = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,productOrder.getOrder_id());
            pst.setInt(2,BeanUser.currentLoginUser.getUser_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanOrderDetail bod = new BeanOrderDetail();
                bod.setProduct_id(rs.getInt(1));
                bod.setProduct_name(rs.getString(2));
                bod.setMerchant_id(rs.getInt(3));
                bod.setOrder_id(rs.getInt(4));
                bod.setProduct_quantity(rs.getInt(5));
                bod.setProduct_sumPrice(rs.getFloat(6));
                bod.setProduct_discountPrice(rs.getFloat(7));
                result.add(bod);
            }
            return result;
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

