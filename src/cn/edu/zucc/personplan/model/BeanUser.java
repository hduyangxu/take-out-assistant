package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanUser {
	private int user_id;
	private String user_name;
	private int user_sex;
	private String user_password;
	private String user_telNumber;
	private String user_email;
	private String user_city;
	private Date user_regDate;
	private int user_isVIP;
	private Date user_vipEndDate;
    public static BeanUser currentLoginUser = null;
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_telNumber() {
		return user_telNumber;
	}

	public void setUser_telNumber(String user_telNumber) {
		this.user_telNumber = user_telNumber;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_city() {
		return user_city;
	}

	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}

	public Date getUser_regDate() {
		return user_regDate;
	}

	public void setUser_regDate(Date user_regDate) {
		this.user_regDate = user_regDate;
	}

	public int getUser_isVIP() {
		return user_isVIP;
	}

	public void setUser_isVIP(int user_isVIP) {
		this.user_isVIP = user_isVIP;
	}

	public Date getUser_vipEndDate() {
		return user_vipEndDate;
	}

	public void setUser_vipEndDate(Date user_vipEndDate) {
		this.user_vipEndDate = user_vipEndDate;
	}
}
