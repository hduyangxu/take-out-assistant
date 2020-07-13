package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanProductOrder;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.BeanSearchProduct;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public  class FrmShowSearchProduct extends JFrame {
    private static final long serialVersionUID = 1L;

    public BeanProductOrder productOrder = FrmShowProductOrder.curProductOrder;

    private Object tblSearchProductTitle[]= BeanSearchProduct.tableTitles;
    private Object tblSearchProductData[][];
    DefaultTableModel tabSearchProductModel=new DefaultTableModel();
    private JTable dataTableSearchProduct=new JTable(tabSearchProductModel);

    private String key=FrmUser.key;
    List<BeanSearchProduct> SearchProduct=null;

    private void reloadSearchProductTable(){
        try {
            SearchProduct= PersonPlanUtil.userManager.loadSearchProduct(key);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblSearchProductData = new Object[SearchProduct.size()][BeanSearchProduct.tableTitles.length];
        for(int i=0;i<SearchProduct.size();i++){
            for(int j=0;j<BeanSearchProduct.tableTitles.length;j++)
                tblSearchProductData[i][j]=SearchProduct.get(i).getCell(j);
        }
        tabSearchProductModel.setDataVector(tblSearchProductData,tblSearchProductTitle);
        this.dataTableSearchProduct.validate();
        this.dataTableSearchProduct.repaint();
    }


    public FrmShowSearchProduct(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("搜索结果");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.reloadSearchProductTable();
        JScrollPane jsDistributeRider=new JScrollPane(this.dataTableSearchProduct);
        this.getContentPane().add(jsDistributeRider);

        this.validate();
    }
}
