package cn.edu.zucc.personplan;

import cn.edu.zucc.personplan.comtrol.example.*;
import cn.edu.zucc.personplan.itf.*;

public class PersonPlanUtil {
	public static IUserManager userManager=new userManager();
	public static IMerchantManager merchantManager=new merchantManager();
	public static IProductTypeManager productTypeManager=new productTypeManager();
	public static IProductDetailsManager productDetailsManager=new productDetailsManager();
	public static IFullReductionManager fullReductionManager=new fullReductionManager();
	public static IDiscountCouponManager discountCouponManager=new discountCouponManager();
	public static IRiderManager riderManager=new riderManager();
	public static IRiderAccountManager riderAccountManager=new riderAccountManager();
	public static IAddressManager addressManager=new addressManager();
	public static IShoppingCartManager shoppingCartManager=new shoppingCartManager();
	public static IUserDiscountCouponManager userDiscountCouponManager=new userDiscountCouponManager();
	public static IOrderManager orderManager=new orderManager();
}
