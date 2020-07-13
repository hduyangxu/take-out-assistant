package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IFullReductionManager;
import cn.edu.zucc.personplan.model.BeanFullReduction;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanProductDetails;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class fullReductionManager implements IFullReductionManager {
    @Override
    public void addFullReduction(BeanMerchant merchant, float fullReduction_request, float fullReduction_money) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="insert into tbl_fullReduction (merchant_id,fullReduction_request,fullReduction_money) values (?,?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            pst.setFloat(2,fullReduction_request);
            pst.setFloat(3,fullReduction_money);
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

    public void modifyReduction(BeanFullReduction fullReduction, float fullReduction_request, float fullReduction_money) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select * from tbl_productorder a,tbl_fullreduction b " +
                    "where a.fullReduction_id=b.fullReduction_id " +
                    "and a.fullReduction_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,fullReduction.getFullReduction_id());
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) throw new BusinessException("订单中已存在该满减方案,无法修改");
            sql="update tbl_fullReduction set fullReduction_request = ?,fullReduction_money = ? where fullReduction_id = ?";
            pst=conn.prepareStatement(sql);
            pst.setFloat(1,fullReduction_request);
            pst.setFloat(2,fullReduction_money);
            pst.setInt(3,fullReduction.getFullReduction_id());
            pst.execute();

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

    public void deleteFullReduction(BeanFullReduction fullReduction) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select * from tbl_productorder a,tbl_fullreduction b " +
                    "where a.fullReduction_id=b.fullReduction_id " +
                    "and a.fullReduction_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,fullReduction.getFullReduction_id());
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) throw new BusinessException("订单中已存在该满减方案,无法删除");
            sql="delete from tbl_fullReduction where fullReduction_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,fullReduction.getFullReduction_id());
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

    public List<BeanFullReduction> loadFullReduction(BeanMerchant beanMerchant) throws BaseException{
        List<BeanFullReduction> result=new ArrayList<BeanFullReduction>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_fullReduction where merchant_id=? group by fullReduction_request";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, beanMerchant.getMerchant_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanFullReduction bfr = new BeanFullReduction();
                bfr.setFullReduction_id(rs.getInt(1));
                bfr.setMerchant_id(rs.getInt(2));
                bfr.setFullReduction_request(rs.getFloat(3));
                bfr.setFullReduction_money(rs.getFloat(4));
                result.add(bfr);
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
