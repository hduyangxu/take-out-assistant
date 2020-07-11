package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanDiscountCoupon;
import cn.edu.zucc.personplan.model.BeanDiscountCouponRequest;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.BeanDiscountCouponRequest;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmShowDiscountCouponRequest extends JFrame {
    private static final long serialVersionUID = 1L;

    private Object tblDiscountCouponRequestTitle[]= BeanDiscountCouponRequest.tableTitles;
    private Object tblDiscountCouponRequestData[][];
    DefaultTableModel tabDiscountCouponRequestModel=new DefaultTableModel();
    private JTable dataTableDiscountCouponRequest=new JTable(tabDiscountCouponRequestModel);

    List<BeanDiscountCouponRequest> discountCouponRequest=null;

    private void reloadDiscountCouponRequest(){
        if(BeanUser.currentLoginUser==null) try {
            throw new BusinessException("当前用户不存在");
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        try {
            discountCouponRequest=PersonPlanUtil.userDiscountCouponManager.loadDiscountCouponRequest(BeanUser.currentLoginUser);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblDiscountCouponRequestData=new Object[discountCouponRequest.size()][BeanDiscountCouponRequest.tableTitles.length];
        for(int i=0;i<discountCouponRequest.size();i++){
            for(int j=0;j<BeanDiscountCouponRequest.tableTitles.length;j++)
                tblDiscountCouponRequestData[i][j]=discountCouponRequest.get(i).getCell(j);
        }
        tabDiscountCouponRequestModel.setDataVector(tblDiscountCouponRequestData,tblDiscountCouponRequestTitle);
        this.dataTableDiscountCouponRequest.validate();
        this.dataTableDiscountCouponRequest.repaint();
    }
    public FrmShowDiscountCouponRequest(){
        this.setTitle(BeanUser.currentLoginUser.getUser_name()+"的集单信息");
        this.getContentPane().add(new JScrollPane(this.dataTableDiscountCouponRequest));
        this.setSize(600, 450);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.validate();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.reloadDiscountCouponRequest();
    }
}
