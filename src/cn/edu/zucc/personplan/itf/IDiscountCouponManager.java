package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanDiscountCoupon;
import cn.edu.zucc.personplan.model.BeanFullReduction;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.Date;
import java.util.List;

public interface IDiscountCouponManager {
    public void addDiscountCoupon(BeanMerchant merchant, float money, Date startDate,
            Date endDate, int request) throws BaseException;

    public void modifyDiscountCoupon(BeanDiscountCoupon discountCoupon, float money, Date startDate,
            Date endDate, int request) throws BaseException;

    public void deleteDiscountCoupon(BeanDiscountCoupon discountCoupon) throws BaseException;

    public List<BeanDiscountCoupon> loadDiscountCoupon(BeanMerchant beanMerchant) throws BaseException;
}
