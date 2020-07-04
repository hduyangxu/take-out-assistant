package cn.edu.zucc.personplan;

import cn.edu.zucc.personplan.comtrol.example.ExampleUserManager;
import cn.edu.zucc.personplan.itf.IUserManager;

public class PersonPlanUtil {
	public static IUserManager userManager=new ExampleUserManager();//需要换成自行设计的实现类
	
}
