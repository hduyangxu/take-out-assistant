package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanOrderDetail;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.BeanUserDiscountCoupon;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IUserDiscountCouponManager {
    public List<BeanUserDiscountCoupon> loadUserDiscountCoupon(BeanUser user) throws BaseException;
}
