package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanProductEvaluate;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmProductEvaluate extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar = new JMenuBar();
    private JPanel workPane = new JPanel();
    private JButton btnEvaluate = new JButton("评价产品");

    private Object tblProductEvaluateTitle[]= BeanProductEvaluate.tableTitles;
    private Object tblProductEvaluateData[][];
    DefaultTableModel tabProductEvaluateModel=new DefaultTableModel();
    private JTable dataTableProductEvaluate=new JTable(tabProductEvaluateModel);

    List<BeanProductEvaluate> ProductEvaluate=null;

    private void reloadProductEvaluate(){
        try {
            ProductEvaluate=PersonPlanUtil.productDetailsManager.loadProductEvaluate();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblProductEvaluateData=new Object[ProductEvaluate.size()][BeanProductEvaluate.tableTitles.length];
        for(int i=0;i<ProductEvaluate.size();i++){
            for(int j=0;j<BeanProductEvaluate.tableTitles.length;j++)
                tblProductEvaluateData[i][j]=ProductEvaluate.get(i).getCell(j);
        }
        tabProductEvaluateModel.setDataVector(tblProductEvaluateData,tblProductEvaluateTitle);
        this.dataTableProductEvaluate.validate();
        this.dataTableProductEvaluate.repaint();
    }
    public FrmProductEvaluate(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle(BeanUser.currentLoginUser.getUser_name()+"的产品评价界面");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        menubar.add(btnEvaluate);
        menubar.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setJMenuBar(menubar);

        this.getContentPane().add(new JScrollPane(this.dataTableProductEvaluate));

        this.reloadProductEvaluate();

        this.btnEvaluate.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnEvaluate){
            int i= FrmProductEvaluate.this.dataTableProductEvaluate.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "请选择商品", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(ProductEvaluate.get(i).getEvaluate_date()!=null){
                JOptionPane.showMessageDialog(null, "产品不能重复评价", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmEvaluateDetails frameEvaluateDetails = new FrmEvaluateDetails(this,"产品评价",true);
            frameEvaluateDetails.productEvaluate=ProductEvaluate.get(i);
            frameEvaluateDetails.setVisible(true);
            this.reloadProductEvaluate();
        }
    }

}
