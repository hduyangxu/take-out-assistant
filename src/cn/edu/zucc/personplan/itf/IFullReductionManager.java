package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanFullReduction;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IFullReductionManager {
    public void addFullReduction(BeanMerchant merchant, float fullReduction_request, float fullReduction_money) throws BaseException;

    public void modifyReduction(BeanFullReduction fullReduction, float fullReduction_request, float fullReduction_money) throws BaseException;

    public void deleteFullReduction(BeanFullReduction fullReduction) throws BaseException;

    public List<BeanFullReduction> loadFullReduction(BeanMerchant beanMerchant) throws BaseException;
}
