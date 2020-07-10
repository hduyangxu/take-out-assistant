package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IOrderManager;
import cn.edu.zucc.personplan.model.BeanMerchant;
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
                    "fullReduction_id,originalPrice,finalPrice,order_startDate,order_requestDate,order_state) values (?,?,?,?,?,?,now(),now(),'等待分配骑手')";

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
}
