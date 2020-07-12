package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanAllProductOrder;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmSysOrder extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar = new JMenuBar();
    private JPanel workPane = new JPanel();
    private JButton btnDistribute = new JButton("分配骑手");


    private Object tblAllProductOrderTitle[]= BeanAllProductOrder.tableTitles;
    private Object tblAllProductOrderData[][];
    DefaultTableModel tabAllProductOrderModel=new DefaultTableModel();
    private JTable dataTableAllProductOrder=new JTable(tabAllProductOrderModel);

    List<BeanAllProductOrder> AllProductOrder=null;

    private void reloadAllProductOrder(){
        try {
            AllProductOrder= PersonPlanUtil.orderManager.loadAllProductOrder();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblAllProductOrderData=new Object[AllProductOrder.size()][BeanAllProductOrder.tableTitles.length];
        for(int i=0;i<AllProductOrder.size();i++){
            for(int j=0;j<BeanAllProductOrder.tableTitles.length;j++)
                tblAllProductOrderData[i][j]=AllProductOrder.get(i).getCell(j);
        }
        tabAllProductOrderModel.setDataVector(tblAllProductOrderData,tblAllProductOrderTitle);
        this.dataTableAllProductOrder.validate();
        this.dataTableAllProductOrder.repaint();
    }
    public FrmSysOrder(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("全部订单");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        menubar.add(btnDistribute);
        this.setJMenuBar(menubar);

        this.getContentPane().add(new JScrollPane(this.dataTableAllProductOrder));

        this.validate();
        this.reloadAllProductOrder();

        this.btnDistribute.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnDistribute){
            int i= FrmSysOrder.this.dataTableAllProductOrder.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "请选择订单", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(AllProductOrder.get(i).getOrder_state().equals("已送出")||AllProductOrder.get(i).getOrder_state().equals("已完成")){
                JOptionPane.showMessageDialog(null, "该订单已被分配", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
                FrmDistributeRider frameDistributeRider = new FrmDistributeRider(this,"请分配骑手",true);
                frameDistributeRider.productOrder=AllProductOrder.get(i);
                frameDistributeRider.setVisible(true);
                this.reloadAllProductOrder();
        }
    }

}
