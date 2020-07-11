package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

import java.sql.Date;

public interface IUserManager {
    //用户注册
  	public BeanUser reg(String userName, String userSex, String userPassword, String userPassword2, String userTelNumber,
						String userEmail, String userCity) throws BaseException;

    //用户登录
	public BeanUser login(String userid,String pwd)throws BaseException;

	//修改姓名
	public void changeName(BeanUser user, String name)throws BaseException;

    //修改密码
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;

	//系统用户登录
	public void systemUserLogin(String systemUserName,String systemUserPassword)throws BaseException;

	//vip管理
	public void vipManager()throws BaseException;
}
