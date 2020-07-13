package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IRiderAccountManager;
import cn.edu.zucc.personplan.model.BeanProductOrder;
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
    *  在订单完成时点击完成订单按钮，获取当前时间，插入入账表的各条信息 1
    *
    *  年月对入账表序号也有影响，比较两条信息的年月是否相等来设置入账序号 1
    *  定义一个特殊的nowOrder来确认该骑手的入账表序号 1
    *
    *  根据入账表序号确认基础单价，判断时间来更新单价，判断好评差评未评价来更新单价1
    */
    public void evaluateRider(BeanProductOrder productOrder, String evaluate) throws BaseException{
        int nowOrder=1;
        int freshman=0;
        float unitPrice=0;
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select max(account_order) from tbl_riderAccount where rider_id = ? " +
                    "and YEAR(finish_date)=YEAR(now()) and MONTH(finish_date)=Month(now())";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,productOrder.getRider_id());
            java.sql.ResultSet rs= pst.executeQuery();
            if(rs.next()) nowOrder=rs.getInt(1)+1;
            sql="select * from tbl_rider where now() < date_add(rider_joinDate, interval 3 month) and rider_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,productOrder.getRider_id());
            rs= pst.executeQuery();
            if(rs.next()) freshman=1;
            if(nowOrder<=100) unitPrice=2;
            else if(nowOrder<=300) unitPrice=3;
            else if(nowOrder<=450) unitPrice=5;
            else if(nowOrder<=499) unitPrice=6;
            else if(nowOrder<=550) unitPrice= (float)6.5;
            else if(nowOrder<=650) unitPrice= (float)7.5;
            else if(nowOrder>650) unitPrice= (float)8.5;
            if(freshman==1) unitPrice= (float) (unitPrice+0.5);
            if(evaluate.equals("好评")) unitPrice= (float) (unitPrice+0.5);
            else if(evaluate.equals("差评")) unitPrice= unitPrice-20;

            sql="insert into tbl_rideraccount(finish_date,account_order,rider_id,order_id,order_userEvaluate,unitPrice) " +
                    "VALUES(now(),?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,nowOrder);
            pst.setInt(2,productOrder.getRider_id());
            pst.setInt(3,productOrder.getOrder_id());
            pst.setString(4,evaluate);
            pst.setFloat(5,unitPrice);
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


    @Override
    public List<BeanRiderAccount> loadRiderAccount(BeanRider rider) throws BaseException {
        List<BeanRiderAccount> result=new ArrayList<BeanRiderAccount>();
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select * from tbl_riderAccount where rider_id = ? group by account_id";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,rider.getRider_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                BeanRiderAccount bra=new BeanRiderAccount();
                bra.setAccount_id(rs.getInt(1));
                bra.setFinish_date(rs.getTimestamp(2));
                bra.setAccount_order(rs.getInt(3));
                bra.setRider_id(rs.getInt(4));
                bra.setOrder_id(rs.getInt(5));
                bra.setOrder_userEvaluate(rs.getString(6));
                bra.setUnitPrice(rs.getInt(7));
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
