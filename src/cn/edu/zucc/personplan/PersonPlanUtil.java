package cn.edu.zucc.personplan;

import cn.edu.zucc.personplan.comtrol.example.merchantManager;
import cn.edu.zucc.personplan.comtrol.example.userManager;
import cn.edu.zucc.personplan.itf.IMerchantManager;
import cn.edu.zucc.personplan.itf.IUserManager;

public class PersonPlanUtil {
	public static IUserManager userManager=new userManager();
	public static IMerchantManager merchantManager=new merchantManager();
	
}
