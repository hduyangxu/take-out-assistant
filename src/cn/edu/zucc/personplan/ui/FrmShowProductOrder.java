package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanProductOrder;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmShowProductOrder extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar = new JMenuBar();
    private JPanel workPane = new JPanel();
    private JButton btnConfirm = new JButton("确认收到");
    private JButton btnOrderDetails = new JButton("查看订单详情");

    private Object tblProductOrderTitle[]= BeanProductOrder.tableTitles;
    private Object tblProductOrderData[][];
    DefaultTableModel tabProductOrderModel=new DefaultTableModel();
    private JTable dataTableProductOrder=new JTable(tabProductOrderModel);

    public static BeanProductOrder curProductOrder = null;

    List<BeanProductOrder> ProductOrder=null;

    private void reloadProductOrder(){
        if(BeanUser.currentLoginUser==null) try {
            throw new BusinessException("当前用户不存在");
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        try {
            ProductOrder= PersonPlanUtil.orderManager.loadProductOrder();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblProductOrderData=new Object[ProductOrder.size()][BeanProductOrder.tableTitles.length];
        for(int i=0;i<ProductOrder.size();i++){
            for(int j=0;j<BeanProductOrder.tableTitles.length;j++)
                tblProductOrderData[i][j]=ProductOrder.get(i).getCell(j);
        }
        tabProductOrderModel.setDataVector(tblProductOrderData,tblProductOrderTitle);
        this.dataTableProductOrder.validate();
        this.dataTableProductOrder.repaint();
    }
    public FrmShowProductOrder(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("查看您的订单");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        menubar.add(btnConfirm);
        menubar.add(btnOrderDetails);
        this.setJMenuBar(menubar);

        this.getContentPane().add(new JScrollPane(this.dataTableProductOrder));
        this.setTitle(BeanUser.currentLoginUser.getUser_name()+"的订单");

        this.validate();
        this.reloadProductOrder();

        this.btnConfirm.addActionListener(this);
        this.btnOrderDetails.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnConfirm){
            int i=FrmShowProductOrder.this.dataTableProductOrder.getSelectedRow();
            if (i<0) {
                JOptionPane.showMessageDialog(null, "请选择订单", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(ProductOrder.get(i).getRider_id()==0){
                JOptionPane.showMessageDialog(null, "商品未送出", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(ProductOrder.get(i).getOrder_state().equals("已完成")){
                JOptionPane.showMessageDialog(null, "商品已确认", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmEvaluate frameEvaluate=new FrmEvaluate(this,"评价骑手",true);
            frameEvaluate.productOrder=ProductOrder.get(i);
            frameEvaluate.setVisible(true);
            reloadProductOrder();
        }else if(e.getSource()==this.btnOrderDetails){
            int i=FrmShowProductOrder.this.dataTableProductOrder.getSelectedRow();
            if (i<0) {
                JOptionPane.showMessageDialog(null, "请选择订单", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(ProductOrder.get(i).getOrder_realDate()==null){
                JOptionPane.showMessageDialog(null, "订单未确认", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            curProductOrder=ProductOrder.get(i);
            FrmShowOrderDetails frameShowOrderDetails = new FrmShowOrderDetails();
            frameShowOrderDetails.setVisible(true);
        }
    }
}
