package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.itf.IMerchantManager;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class merchantManager implements IMerchantManager{
    @Override
    public void addMerchant(String merchant_name, String starRated) throws BaseException{
        if(merchant_name==null||"".equals(merchant_name)) throw new BusinessException("商家名称不能为空");
        Connection conn=null;
        try {
            conn=DBUtil2.getConnection();
            String sql="insert into tbl_merchant (merchant_name,merchant_starRated) values (?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,merchant_name);
            pst.setInt(2, Integer.parseInt(starRated));
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

    @Override
    public void modifyMerchant(BeanMerchant curMerchant, String merchant_name, String starRated) throws BaseException{
        if(merchant_name==null||"".equals(merchant_name)) throw new BusinessException("商家名称不能为空");
        Connection conn=null;
        try {
            conn=DBUtil2.getConnection();
            String sql="update tbl_merchant set merchant_name=? where merchant_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,merchant_name);
            pst.setInt(2,curMerchant.getMerchant_id());
            pst.execute();
            sql="update tbl_merchant set merchant_starRated=? where merchant_id=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(starRated));
            pst.setInt(2, curMerchant.getMerchant_id());
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

    @Override
    public void deleteMerchant(BeanMerchant curMerchant) throws BaseException{
        Connection conn=null;
        try {
            conn=DBUtil2.getConnection();
            String sql="select * from tbl_merchant,tbl_productType where tbl_productType.merchant_id = tbl_merchant.merchant_id" +
                    " and tbl_merchant.merchant_id = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,curMerchant.getMerchant_id());
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()) throw new BusinessException("无法删除，该商家下尚有商品分类");
            sql="select * from tbl_merchant,tbl_fullReduction where tbl_fullReduction.merchant_id = tbl_merchant.merchant_id" +
                    " and tbl_merchant.merchant_id = ?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,curMerchant.getMerchant_id());
            rs=pst.executeQuery();
            if(rs.next()) throw new BusinessException("无法删除，该商家下尚有满减信息");
            sql="select * from tbl_merchant,tbl_discountCoupon where tbl_discountCoupon.merchant_id = tbl_merchant.merchant_id" +
                    " and tbl_merchant.merchant_id = ?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,curMerchant.getMerchant_id());
            rs=pst.executeQuery();
            if(rs.next()) throw new BusinessException("无法删除，该商家下尚有优惠券信息");
            sql = "delete from tbl_merchant where merchant_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,curMerchant.getMerchant_id());
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

    @Override
    public List<BeanMerchant> loadAllMerchant() throws BaseException{
        List<BeanMerchant> result=new ArrayList<BeanMerchant>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_merchant";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
              BeanMerchant bm = new BeanMerchant();
              bm.setMerchant_id(rs.getInt(1));
              bm.setMerchant_name(rs.getString(2));
              bm.setMerchant_starRated(rs.getInt(3));
              bm.setMerchant_avgConsumption(rs.getFloat(4));
              bm.setMerchant_totalSales(rs.getInt(5));
              result.add(bm);
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
