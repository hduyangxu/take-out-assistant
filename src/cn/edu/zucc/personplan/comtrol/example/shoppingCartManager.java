package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IShoppingCartManager;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanOrderDetail;
import cn.edu.zucc.personplan.model.BeanProductDetails;
import cn.edu.zucc.personplan.model.BeanRider;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class shoppingCartManager implements IShoppingCartManager {
    public void addToShoppingCart(BeanProductDetails productDetails, int count) throws BaseException{
        if(count<=0) throw new BusinessException("购买数量必须大于0");
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            int nowOrderId = 1;
            String sql="select max(order_id) from tbl_productOrder";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) nowOrderId=rs.getInt(1)+1;
            sql="select * from tbl_orderdetail where product_id=? and order_id=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,productDetails.getProduct_id());
            pst.setInt(2,nowOrderId);
            rs=pst.executeQuery();
            if(rs.next()){
                sql="update tbl_orderdetail set product_quantity=product_quantity+?,product_sumPrice=" +
                        "product_sumPrice+?,product_discountPrice=product_discountPrice+? where order_id=? and product_id=?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,count);
                pst.setFloat(2,productDetails.getProduct_price()*count);
                pst.setFloat(3,productDetails.getProduct_discountPrice()*count);
                pst.setInt(4,nowOrderId);
                pst.setInt(5,productDetails.getProduct_id());
                pst.execute();
            }else{
                sql="insert into tbl_orderdetail values (?,?,?,?,?,?,?)";
                pst=conn.prepareStatement(sql);
                pst.setInt(1,productDetails.getProduct_id());
                pst.setString(2,productDetails.getProduct_name());
                pst.setInt(3,productDetails.getMerchant_id());
                pst.setInt(4,nowOrderId);
                pst.setInt(5,count);
                pst.setFloat(6,productDetails.getProduct_price()*count);
                pst.setFloat(7,productDetails.getProduct_discountPrice()*count);
                pst.execute();
            }
            rs.close();
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

    public void removeFromShoppingCart(BeanOrderDetail orderDetail) throws BaseException{
        Connection conn=null;
        try {
            conn= DBUtil2.getConnection();
            String sql="delete from tbl_orderDetail where product_id = ? and order_id = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,orderDetail.getProduct_id());
            pst.setInt(2,orderDetail.getOrder_id());
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

    public List<BeanOrderDetail> loadShoppingCart(BeanMerchant merchant) throws BaseException{
        List<BeanOrderDetail> result=new ArrayList<BeanOrderDetail>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_orderDetail where merchant_id = ? and order_id=(select max(order_id) from tbl_orderDetail)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
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
