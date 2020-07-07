package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IRiderManager;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanRider;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class riderManager implements IRiderManager {
    @Override
    public void addRider(String rider_name) throws BaseException{
        if(rider_name==null||"".equals(rider_name)) throw new BusinessException("骑手名称不能为空");
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="insert into tbl_rider (rider_name,rider_joinDate,rider_identification) values (?,now(),'新人')";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,rider_name);
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
    public void modifyRider(BeanRider rider, String rider_name) throws BaseException{
        if(rider_name==null||"".equals(rider_name)) throw new BusinessException("骑手名称不能为空");
        Connection conn=null;
        try {
            conn=DBUtil2.getConnection();
            String sql="update tbl_rider set rider_name=? where rider_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,rider_name);
            pst.setInt(2,rider.getRider_id());
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
    public void deleteRider(BeanRider rider) throws BaseException{
        Connection conn=null;
        try {
            conn=DBUtil2.getConnection();
            String sql="select * from tbl_rider a,tbl_riderAccount b where a.rider_id = b.rider_id" +
                    " and a.rider_id = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,rider.getRider_id());
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()) throw new BusinessException("无法删除，该骑手尚有入帐单");
            sql = "delete from tbl_rider where rider_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,rider.getRider_id());
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
    public List<BeanRider> loadAllRider() throws BaseException{
        List<BeanRider> result=new ArrayList<BeanRider>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_rider";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanRider br=new BeanRider();
                br.setRider_id(rs.getInt(1));
                br.setRider_name(rs.getString(2));
                br.setRider_joinDate(rs.getTimestamp(3));
                br.setRider_identification(rs.getString(4));
                result.add(br);
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
