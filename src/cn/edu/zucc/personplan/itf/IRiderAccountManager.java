package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanRider;
import cn.edu.zucc.personplan.model.BeanRiderAccount;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IRiderAccountManager {
    public List<BeanRiderAccount> loadRiderAccount(BeanRider rider) throws BaseException;
}
