package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IAddressManager;
import cn.edu.zucc.personplan.model.BeanAddress;
import cn.edu.zucc.personplan.model.BeanDiscountCoupon;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class addressManager implements IAddressManager {
    public void addAddress(BeanUser user, String province, String city, String zone, String detail, String people, String tel) throws BaseException {
        if (detail == null || "".equals(detail)) throw new BusinessException("详细地址不能为空");
        if (people == null || "".equals(people)) throw new BusinessException("联系人不能为空");
        if (tel == null || "".equals(tel)) throw new BusinessException("联系电话不能为空");
        Connection conn = null;
        try {
            conn = DBUtil2.getConnection();
            String sql = "insert into tbl_address(user_id,address_province,address_city" +
                    ",address_zone,address_detail,address_people,address_tel) values (?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, user.getUser_id());
            pst.setString(2, province);
            pst.setString(3, city);
            pst.setString(4, zone);
            pst.setString(5, detail);
            pst.setString(6, people);
            pst.setString(7, tel);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

    }

    public void deleteAddress(BeanAddress address) throws BaseException {
        Connection conn = null;
        try {
            conn = DBUtil2.getConnection();
            String sql = "delete from tbl_address where address_id = " + address.getAddress_id();
            java.sql.Statement st = conn.createStatement();
            st.execute(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    public void modifyAddress(BeanAddress address, String province, String city, String zone, String detail, String people, String tel) throws BaseException {
        if (detail == null || "".equals(detail)) throw new BusinessException("详细地址不能为空");
        if (people == null || "".equals(people)) throw new BusinessException("联系人不能为空");
        if (tel == null || "".equals(tel)) throw new BusinessException("联系电话不能为空");
        Connection conn = null;
        try {
            conn = DBUtil2.getConnection();
            String sql = "update tbl_address set address_province=?,address_city=?," +
                    "address_zone=?,address_detail=?,address_people=?,address_tel=? where address_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, province);
            pst.setString(2, city);
            pst.setString(3, zone);
            pst.setString(4, detail);
            pst.setString(5, people);
            pst.setString(6, tel);
            pst.setInt(7, address.getAddress_id());
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    public List<BeanAddress> loadAddress(BeanUser user) throws BaseException {
        List<BeanAddress> result = new ArrayList<BeanAddress>();
        Connection conn = null;
        try {
            conn = DBUtil2.getConnection();
            String sql = "select * from tbl_address where user_id = " + user.getUser_id();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BeanAddress ba = new BeanAddress();
                ba.setAddress_id(rs.getInt(1));
                ba.setUser_id(rs.getInt(2));
                ba.setAddress_province(rs.getString(3));
                ba.setAddress_city(rs.getString(4));
                ba.setAddress_zone(rs.getString(5));
                ba.setAddress_detail(rs.getString(6));
                ba.setAddress_people(rs.getString(7));
                ba.setAddress_tel(rs.getString(8));
                result.add(ba);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}