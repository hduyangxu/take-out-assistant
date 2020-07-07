package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanProductType;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IProductTypeManager {
    //添加商品类别
    public void addProductType(BeanMerchant curMerchant,String type_name) throws BaseException;

    public void deleteProductType(BeanProductType curProductType) throws BaseException;

    public void modifyProductType(BeanProductType curProductType, String type_name) throws BaseException;

    public List<BeanProductType> loadProductType(BeanMerchant merchant) throws BaseException;




}
