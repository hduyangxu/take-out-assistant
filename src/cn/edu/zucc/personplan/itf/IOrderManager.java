package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IOrderManager {
    public void addOrder(BeanMerchant merchant, int addressId, int discountCouponId, int fullReductionId, float sumPrice,
                         float finalPrice)throws BaseException;

    public void confirmArrive(BeanProductOrder productOrder) throws BaseException;

    public void distributeRider(BeanAllProductOrder productOrder, BeanRider rider) throws  BaseException;

    public List<BeanProductOrder> loadProductOrder() throws BaseException;

    public List<BeanAllProductOrder> loadAllProductOrder() throws BaseException;

    public List<BeanOrderDetail> loadOrderDetails(BeanProductOrder productOrder) throws BaseException;
}
