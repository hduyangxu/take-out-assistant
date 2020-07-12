package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanAllProductOrder;
import cn.edu.zucc.personplan.model.BeanOrderDetail;
import cn.edu.zucc.personplan.model.BeanProductOrder;
import cn.edu.zucc.personplan.model.BeanRider;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmShowOrderDetails  extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    public BeanProductOrder productOrder = FrmShowProductOrder.curProductOrder;

    private Object tblOrderDetailTitle[]= BeanOrderDetail.tableTitles;
    private Object tblOrderDetailData[][];
    DefaultTableModel tabOrderDetailModel=new DefaultTableModel();
    private JTable dataTableOrderDetail=new JTable(tabOrderDetailModel);


    List<BeanOrderDetail> orderDetail=null;

    private void reloadOrderDetailTable(){
        try {
            orderDetail=PersonPlanUtil.orderManager.loadOrderDetails(productOrder);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblOrderDetailData = new Object[orderDetail.size()][BeanOrderDetail.tableTitles.length];
        for(int i=0;i<orderDetail.size();i++){
            for(int j=0;j<BeanOrderDetail.tableTitles.length;j++)
                tblOrderDetailData[i][j]=orderDetail.get(i).getCell(j);
        }
        tabOrderDetailModel.setDataVector(tblOrderDetailData,tblOrderDetailTitle);
        this.dataTableOrderDetail.validate();
        this.dataTableOrderDetail.repaint();
    }


    public FrmShowOrderDetails(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("订单详情");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.reloadOrderDetailTable();
        JScrollPane jsDistributeRider=new JScrollPane(this.dataTableOrderDetail);
        this.getContentPane().add(jsDistributeRider);


        this.validate();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
