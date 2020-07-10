package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.util.BaseException;

public interface IOrderManager {
    public void addOrder(BeanMerchant merchant, int addressId, int discountCouponId, int fullReductionId, float sumPrice,
                         float finalPrice)throws BaseException;
}
