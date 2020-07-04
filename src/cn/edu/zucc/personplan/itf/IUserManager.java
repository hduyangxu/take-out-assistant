package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

import java.sql.Date;

public interface IUserManager {
	/**
	 * 注册：
	 * 要求用户名不能重复，不能为空
	 * 两次输入的密码必须一致，密码不能为空
	 * 如果注册失败，则抛出异常
	 * @param userName 用户名
	 * @param userSex  性别
	 * @param userPassword  密码
	 * @param userPassword2 确认密码
	 * @param userTelNumber  手机
	 * @param userEmail  邮箱
	 * @param userCity  城市
	 * @return
	 * @throws BaseException
	 */
	public BeanUser reg(String userName, String userSex, String userPassword, String userPassword2, String userTelNumber,
						String userEmail, String userCity) throws BaseException;
	/**
	 * 登陆
	 * 1、如果用户不存在或者密码错误，抛出一个异常
	 * 2、如果认证成功，则返回当前用户信息
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanUser login(String userid,String pwd)throws BaseException;
	/**
	 * 修改密码
	 * 如果没有成功修改，则抛出异常
	 * @param user    当前用户
	 * @param oldPwd  原密码
	 * @param newPwd  新密码
	 * @param newPwd2 重复输入的新密码
	 */
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
}
