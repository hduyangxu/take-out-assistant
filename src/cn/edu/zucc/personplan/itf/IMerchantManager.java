package cn.edu.zucc.personplan.itf;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

import java.sql.Date;
import java.util.List;

public interface IMerchantManager {
    //添加商家
    public void addMerchant(String merchant_name, String starRated) throws BaseException;

    public void modifyMerchant(BeanMerchant curMerchant, String merchant_name, String starRated) throws BaseException;

    public void deleteMerchant(BeanMerchant curMerchant) throws BaseException;

    public List<BeanMerchant> loadAll() throws BaseException;


}
