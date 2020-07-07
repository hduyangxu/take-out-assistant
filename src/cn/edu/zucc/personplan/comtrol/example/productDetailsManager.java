package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IProductDetailsManager;
import cn.edu.zucc.personplan.itf.IProductTypeManager;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanProductDetails;
import cn.edu.zucc.personplan.model.BeanProductType;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productDetailsManager implements IProductDetailsManager {

    @Override
    public void addProduct(BeanProductType productType, String product_name,
                           float product_price, float product_discountPrice) throws BaseException{
        if(product_name==null||"".equals(product_name)) throw new BusinessException("商品名称不能为空");
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="insert into tbl_productDetails (merchant_id,type_id,product_name, " +
                    "product_price,product_discountPrice) values (?,?,?,?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,productType.getMerchant_id());
            pst.setInt(2,productType.getType_id());
            pst.setString(3,product_name);
            pst.setFloat(4,product_price);
            pst.setFloat(5,product_discountPrice);
            pst.execute();
            sql="update tbl_productType set type_quantity=type_quantity+1 where type_id = ?";
            pst=conn.prepareStatement(sql);
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
    public void deleteProduct(BeanProductDetails productDetails) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="delete from tbl_productDetails where product_id = "+productDetails.getProduct_id();
            java.sql.Statement st = conn.createStatement();
            st.execute(sql);
            sql="update tbl_productType set type_quantity=type_quantity-1 where type_id = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst=conn.prepareStatement(sql);
            pst.setInt(1,productDetails.getType_id());
            pst.execute();
            st.close();
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
    public void modifyProductDetails(BeanProductDetails productDetails, String product_name,
                                     float product_price, float product_discountPrice) throws BaseException{
        if(product_name==null||"".equals(product_name)) throw new BusinessException("商品名称不能为空");
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="update tbl_productDetails set product_name = ? where product_id = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,product_name);
            pst.setInt(2,productDetails.getProduct_id());
            pst.execute();
            sql="update tbl_productDetails set product_price = ? where product_id = ?";
            pst=conn.prepareStatement(sql);
            pst.setFloat(1,product_price);
            pst.setInt(2,productDetails.getProduct_id());
            pst.execute();
            sql="update tbl_productDetails set product_discountPrice = ? where product_id = ?";
            pst=conn.prepareStatement(sql);
            pst.setFloat(1,product_discountPrice);
            pst.setInt(2,productDetails.getProduct_id());
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
    public List<BeanProductDetails> loadProductDetails(BeanProductType productType) throws BaseException{
        List<BeanProductDetails> result=new ArrayList<BeanProductDetails>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_productDetails where type_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,productType.getType_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanProductDetails bpd = new BeanProductDetails();
                bpd.setProduct_id(rs.getInt(1));
                bpd.setMerchant_id(rs.getInt(2));
                bpd.setType_id(rs.getInt(3));
                bpd.setProduct_name(rs.getString(4));
                bpd.setProduct_price(rs.getFloat(5));
                bpd.setProduct_discountPrice(rs.getFloat(6));
                result.add(bpd);
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
