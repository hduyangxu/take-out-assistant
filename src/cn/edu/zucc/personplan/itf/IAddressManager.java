package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanAddress;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanProductType;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IAddressManager {
    public void addAddress(BeanUser user, String province, String city, String zone, String detail, String people, String tel) throws BaseException;

    public void deleteAddress(BeanAddress address) throws BaseException;

    public void modifyAddress(BeanAddress address, String province, String city, String zone, String detail, String people, String tel) throws BaseException;

    public List<BeanAddress> loadAddress(BeanUser user) throws BaseException;

}
