package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IRiderAccountManager;
import cn.edu.zucc.personplan.model.BeanRider;
import cn.edu.zucc.personplan.model.BeanRiderAccount;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class riderAccountManager implements IRiderAccountManager {
    /*
    *  在订单完成时点击完成订单按钮，获取当前时间，插入入账表的各条信息
    *
    *  年月对入账表序号也有影响，比较两条信息的年月是否相等来设置入账序号
    *  定义一个特殊的nowOrder来确认该骑手的入账表序号
    *
    *  根据入账表序号确认基础单价，判断时间来更新单价，判断好评差评未评价来更新单价
    *  年月相对骑手的注册时间更新单价
    */
    @Override
    public List<BeanRiderAccount> loadRiderAccount(BeanRider rider) throws BaseException {
        List<BeanRiderAccount> result=new ArrayList<BeanRiderAccount>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_riderAccount group by account_id";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanRiderAccount bra=new BeanRiderAccount();
                bra.setAccount_id(rs.getInt(1));
                bra.setRider_id(rs.getInt(2));
                bra.setOrder_id(rs.getInt(3));
                bra.setOrder_userEvaluate(rs.getString(4));
                bra.setUnitPrice(rs.getInt(5));
                bra.setYearMonth(rs.getInt(6));
                bra.setAccount_order(rs.getInt(7));
                result.add(bra);
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
