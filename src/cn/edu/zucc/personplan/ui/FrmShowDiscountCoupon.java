package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmShowDiscountCoupon extends JFrame{
    private static final long serialVersionUID = 1L;

    private Object tblUserDiscountCouponTitle[]=BeanUserDiscountCoupon.tableTitles;
    private Object tblUserDiscountCouponData[][];
    DefaultTableModel tabUserDiscountCouponModel=new DefaultTableModel();
    private JTable dataTableUserDiscountCoupon=new JTable(tabUserDiscountCouponModel);

    List<BeanUserDiscountCoupon> userDiscountCoupon=null;

    private void reloadUserDiscountCoupon(){
        if(BeanUser.currentLoginUser==null) try {
            throw new BusinessException("当前用户不存在");
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        try {
            userDiscountCoupon=PersonPlanUtil.userDiscountCouponManager.loadUserDiscountCoupon(BeanUser.currentLoginUser);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblUserDiscountCouponData=new Object[userDiscountCoupon.size()][BeanUserDiscountCoupon.tableTitles.length];
        for(int i=0;i<userDiscountCoupon.size();i++){
            for(int j=0;j<BeanUserDiscountCoupon.tableTitles.length;j++)
                tblUserDiscountCouponData[i][j]=userDiscountCoupon.get(i).getCell(j);
        }
        tabUserDiscountCouponModel.setDataVector(tblUserDiscountCouponData,tblUserDiscountCouponTitle);
        this.dataTableUserDiscountCoupon.validate();
        this.dataTableUserDiscountCoupon.repaint();
    }
    public FrmShowDiscountCoupon(){
        this.setTitle(BeanUser.currentLoginUser.getUser_name()+"的优惠券");
        this.getContentPane().add(new JScrollPane(this.dataTableUserDiscountCoupon));
        this.setSize(600, 450);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.validate();
        this.reloadUserDiscountCoupon();
    }

}
