package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IProductTypeManager;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanProductType;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productTypeManager implements IProductTypeManager {
    @Override
    public void addProductType(BeanMerchant Merchant,String type_name) throws BaseException{
        if(type_name==null||"".equals(type_name)) throw new BusinessException("类别名称不能为空");
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="insert into tbl_productType (type_name,type_quantity,merchant_id) values (?,0,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,type_name);
            pst.setInt(2, Merchant.getMerchant_id());
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
    public void deleteProductType(BeanProductType productType) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="select * from tbl_productType a, tbl_productDetails b where a.type_id = b.type_id and a.type_id = "+productType.getType_id();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if(rs.next()) throw new BusinessException("无法删除，该类别下尚有商品");
            st.close();
            rs.close();
            sql="delete from tbl_productType where type_id = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,productType.getType_id());
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
    public void modifyProductType(BeanProductType productType, String type_name) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="update tbl_productType set type_name = ? where type_id = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,type_name);
            pst.setInt(2,productType.getType_id());
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
    public List<BeanProductType> loadProductType(BeanMerchant merchant) throws BaseException{
        List<BeanProductType> result=new ArrayList<BeanProductType>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_productType where merchant_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanProductType bpt = new BeanProductType();
                bpt.setType_id(rs.getInt(1));
                bpt.setType_name(rs.getString(2));
                bpt.setType_quantity(rs.getInt(3));
                bpt.setMerchant_id(rs.getInt(4));
                result.add(bpt);
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
