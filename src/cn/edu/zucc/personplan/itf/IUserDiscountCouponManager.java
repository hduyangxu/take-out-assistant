package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IUserDiscountCouponManager {
    public List<BeanUserDiscountCoupon> loadUserDiscountCoupon(BeanUser user) throws BaseException;

    public List<BeanDiscountCouponRequest> loadDiscountCouponRequest(BeanUser user) throws BaseException;
}
