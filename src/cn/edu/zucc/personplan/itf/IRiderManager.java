package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanRider;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IRiderManager {
    public void addRider(String rider_name) throws BaseException;

    public void modifyRider(BeanRider rider, String rider_name) throws BaseException;

    public void deleteRider(BeanRider rider) throws BaseException;

    public List<BeanRider> loadAllRider() throws BaseException;
}
