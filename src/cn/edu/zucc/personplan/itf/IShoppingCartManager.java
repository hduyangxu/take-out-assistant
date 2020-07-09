package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanOrderDetail;
import cn.edu.zucc.personplan.model.BeanProductDetails;
import cn.edu.zucc.personplan.model.BeanRider;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IShoppingCartManager {
    public void addToShoppingCart(BeanProductDetails productDetails, int count) throws BaseException;

    public void removeFromShoppingCart(BeanOrderDetail orderDetail) throws BaseException;

    public List<BeanOrderDetail> loadShoppingCart(BeanMerchant merchant) throws BaseException;
}
