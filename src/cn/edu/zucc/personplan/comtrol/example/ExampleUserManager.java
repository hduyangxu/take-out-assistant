package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class ExampleUserManager implements IUserManager {

	@Override
	public BeanUser reg(String userName, String userSex, String userPassword, String userPassword2, String userTelNumber,
						String userEmail, String userCity) throws BaseException{
		// TODO Auto-generated method stub
		if("".equals(userName)) throw new BusinessException("用户名不能为空");
		if("".equals(userTelNumber)) throw new BusinessException("请填写手机号");
		if("".equals(userPassword)) throw new BusinessException("密码不能为空");
		if(!userPassword.equals(userPassword2)) throw new BusinessException("两次输入密码不一致");
		if(userTelNumber.length() != 11) throw new BusinessException("手机号应为11位");
		Connection conn = null;
		try {
			conn = DBUtil2.getConnection();
			String sql = "select * from tbl_user where user_name=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("该账号已被注册");
			rs.close();
			pst.close();
			sql = "insert into tbl_user(user_name,user_sex,user_password,user_telNumber,user_email,user_city,user_regDate,user_isVIP,user_vipEndDate) " +
					"values(?, ?, ?, ?, ?, ?, now(), null , null)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			pst.setString(2, userSex);
			pst.setString(3, userPassword);
			pst.setString(4, userTelNumber);
			pst.setString(5, userEmail);
			pst.setString(6, userCity);
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
		return null;
	}

	
	@Override
	public BeanUser login(String userName, String userPassword) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn= DBUtil2.getConnection();
			String sql="select user_name,user_password,user_id from tbl_user where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userName);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
     		if(!rs.getString(2).equals(userPassword))throw new BusinessException("密码错误");
			BeanUser u=new BeanUser();
			u.setUser_name(rs.getString(1));
			u.setUser_id(rs.getInt(3));
			rs.close();
			pst.close();
			return u;
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
	public void changePwd(BeanUser user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>16) throw new BusinessException("必须为1-16个字符");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_pwd from tbl_user where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,user.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			if(!newPwd.equals(newPwd2)) throw new BusinessException("输入的新密码两次不一致");
			rs.close();
			pst.close();
			sql="update tbl_user set user_password=? where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, user.getUser_id());
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
