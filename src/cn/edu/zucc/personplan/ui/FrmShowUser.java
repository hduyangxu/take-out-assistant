package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUserDetail;
import cn.edu.zucc.personplan.model.BeanProductOrder;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmShowUser extends JFrame {
    private static final long serialVersionUID = 1L;

    public BeanProductOrder productOrder = FrmShowProductOrder.curProductOrder;

    private Object tblUserDetailTitle[]= BeanUserDetail.tableTitles;
    private Object tblUserDetailData[][];
    DefaultTableModel tabUserDetailModel=new DefaultTableModel();
    private JTable dataTableUserDetail=new JTable(tabUserDetailModel);


    List<BeanUserDetail> UserDetail=null;

    private void reloadUserDetailTable(){
        try {
            UserDetail= PersonPlanUtil.userManager.loadUserDetails();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblUserDetailData = new Object[UserDetail.size()][BeanUserDetail.tableTitles.length];
        for(int i=0;i<UserDetail.size();i++){
            for(int j=0;j<BeanUserDetail.tableTitles.length;j++)
                tblUserDetailData[i][j]=UserDetail.get(i).getCell(j);
        }
        tabUserDetailModel.setDataVector(tblUserDetailData,tblUserDetailTitle);
        this.dataTableUserDetail.validate();
        this.dataTableUserDetail.repaint();
    }


    public FrmShowUser(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("用户信息");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.reloadUserDetailTable();
        JScrollPane jsDistributeRider=new JScrollPane(this.dataTableUserDetail);
        this.getContentPane().add(jsDistributeRider);
        this.validate();
    }

}
