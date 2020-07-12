package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IUserDiscountCouponManager;
import cn.edu.zucc.personplan.model.BeanDiscountCouponRequest;
import cn.edu.zucc.personplan.model.BeanOrderDetail;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.BeanUserDiscountCoupon;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDiscountCouponManager implements IUserDiscountCouponManager {
    public List<BeanUserDiscountCoupon> loadUserDiscountCoupon(BeanUser user) throws BaseException{
        List<BeanUserDiscountCoupon> result=new ArrayList<BeanUserDiscountCoupon>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_userDiscountCoupon where user_id = ? group by discountCoupon_id";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,user.getUser_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanUserDiscountCoupon budc=new BeanUserDiscountCoupon();
                budc.setUser_id(rs.getInt(1));
                budc.setDiscountCoupon_id(rs.getInt(2));
                budc.setDiscountCoupon_count(rs.getInt(3));
                budc.setMerchant_name(rs.getString(4));
                budc.setDiscountCoupon_money(rs.getFloat(5));
                budc.setDiscountCoupon_isConflict(rs.getString(6));
                result.add(budc);
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
    public List<BeanDiscountCouponRequest> loadDiscountCouponRequest(BeanUser user) throws BaseException{
        List<BeanDiscountCouponRequest> result=new ArrayList<BeanDiscountCouponRequest>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_DiscountCouponRequest where user_id = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,user.getUser_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanDiscountCouponRequest bdcq=new BeanDiscountCouponRequest();
                bdcq.setUser_id(rs.getInt(1));
                bdcq.setMerchant_id(rs.getInt(2));
                bdcq.setDiscountCoupon_id(rs.getInt(3));
                bdcq.setDiscountCoupon_already(rs.getInt(4));
                bdcq.setDiscountCoupon_request(rs.getInt(5));
                bdcq.setDiscountCoupon_money(rs.getFloat(6));
                bdcq.setDiscountCoupon_isConflict(rs.getString(7));
                result.add(bdcq);
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
