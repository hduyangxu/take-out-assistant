package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanFullReduction;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanProductDetails;
import cn.edu.zucc.personplan.model.BeanProductType;
import cn.edu.zucc.personplan.util.BaseException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class FrmSys extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_merchant=new JMenu("商家管理");
    private JMenu menu_product=new JMenu("产品管理");
    private JMenu menu_fullReduction=new JMenu("满减管理");
    private JMenu menu_discountCoupon=new JMenu("优惠券管理");

    private JMenuItem  menuItem_showMerchant=new JMenuItem("显示商家详情");
    private JMenuItem  menuItem_addMerchant=new JMenuItem("添加商家");
    private JMenuItem  menuItem_deleteMerchant=new JMenuItem("删除商家");
    private JMenuItem  menuItem_modifyMerchant=new JMenuItem("修改商家信息");
    private JMenuItem  menuItem_addProductType=new JMenuItem("添加产品类型");
    private JMenuItem  menuItem_deleteProductType=new JMenuItem("删除产品类型");
    private JMenuItem  menuItem_modifyProductType=new JMenuItem("修改产品类型");
    private JMenuItem  menuItem_addProduct=new JMenuItem("添加产品");
    private JMenuItem  menuItem_deleteProduct=new JMenuItem("删除产品");
    private JMenuItem  menuItem_modifyProduct=new JMenuItem("修改产品");
    private JMenuItem  menuItem_addFullReduction=new JMenuItem("添加满减");
    private JMenuItem  menuItem_deleteFullReduction=new JMenuItem("删除满减");
    private JMenuItem  menuItem_modifyFullReduction=new JMenuItem("修改满减");
    private JMenuItem  menuItem_addDiscountCoupon=new JMenuItem("添加优惠券");
    private JMenuItem  menuItem_deleteDiscountCoupon=new JMenuItem("删除优惠券");
    private JMenuItem  menuItem_modifyDiscountCoupon=new JMenuItem("修改优惠券");

    private JPanel activityBar = new JPanel();

    private Object tblMerchantTitle[]= BeanMerchant.tableTitles;
    private Object tblMerchantData[][];
    DefaultTableModel tabMerchantModel=new DefaultTableModel();
    private JTable dataTableMerchant=new JTable(tabMerchantModel);

    private Object tblProductTypeTitle[]= BeanProductType.tableTitles;
    private Object tblProductTypeData[][];
    DefaultTableModel tabProductTypeModel=new DefaultTableModel();
    private JTable dataTableProductType=new JTable(tabProductTypeModel);

    private Object tblProductDetailsTitle[]= BeanProductDetails.tableTitles;
    private Object tblProductDetailsData[][];
    DefaultTableModel tabProductDetailsModel=new DefaultTableModel();
    private JTable dataTableProductDetails=new JTable(tabProductDetailsModel);

    private Object tblFullReductionTitle[]= BeanFullReduction.tableTitles;
    private Object tblFullReductionData[][];
    DefaultTableModel tabFullReductionModel=new DefaultTableModel();
    private JTable dataTableFullReduction=new JTable(tabFullReductionModel);

    private BeanMerchant curMerchant=null;
    private BeanProductType curProductType=null;

    List<BeanMerchant> allMerchant=null;
    List<BeanProductType> productType=null;
    List<BeanProductDetails> productDetails=null;
    List<BeanFullReduction> fullReduction=null;

    private void reloadMerchantTable(){//重新载入商家信息
        try {
            allMerchant=PersonPlanUtil.merchantManager.loadAllMerchant();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblMerchantData=new Object[allMerchant.size()][BeanMerchant.tableTitles.length];
        for(int i=0;i<allMerchant.size();i++){
            for(int j=0;j<BeanMerchant.tableTitles.length;j++)
                tblMerchantData[i][j]=allMerchant.get(i).getCell(j);
        }
        tabMerchantModel.setDataVector(tblMerchantData,tblMerchantTitle);
        this.dataTableMerchant.validate();
        this.dataTableMerchant.repaint();
    }

    private void reloadProductTypeTable(int merchantIdx){
        if(merchantIdx<0) return;
        curMerchant=allMerchant.get(merchantIdx);
        try {
            productType=PersonPlanUtil.productTypeManager.loadProductType(curMerchant);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblProductTypeData = new Object[productType.size()][BeanProductType.tableTitles.length];
        for(int i=0;i<productType.size();i++){
            for(int j=0;j<BeanProductType.tableTitles.length;j++)
                tblProductTypeData[i][j]=productType.get(i).getCell(j);
        }
        tabProductTypeModel.setDataVector(tblProductTypeData,tblProductTypeTitle);
        this.dataTableProductType.validate();
        this.dataTableProductType.repaint();
    }

    private void reloadProductDetailsTable(int productTypeIdx){
        if(productTypeIdx<0) return;
        curProductType=productType.get(productTypeIdx);
        try {
            productDetails=PersonPlanUtil.productDetailsManager.loadProductDetails(curProductType);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblProductDetailsData =new Object[productDetails.size()][BeanProductDetails.tableTitles.length];
        for(int i=0;i<productDetails.size();i++){
            for(int j=0;j<BeanProductDetails.tableTitles.length;j++)
                tblProductDetailsData[i][j]=productDetails.get(i).getCell(j);
        }

        tabProductDetailsModel.setDataVector(tblProductDetailsData,tblProductDetailsTitle);
        this.dataTableProductDetails.validate();
        this.dataTableProductDetails.repaint();
    }

    private void reloadFullReduction(int merchantIdx){
        if(merchantIdx<0) return;
        curMerchant=allMerchant.get(merchantIdx);
        try {
             fullReduction=PersonPlanUtil.fullReductionManager.loadFullReduction(curMerchant);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblFullReductionData =new Object[fullReduction.size()][BeanFullReduction.tableTitles.length];
        for(int i=0;i<fullReduction.size();i++){
            for(int j=0;j<BeanFullReduction.tableTitles.length;j++)
                tblFullReductionData[i][j]=fullReduction.get(i).getCell(j);
        }

        tabFullReductionModel.setDataVector(tblFullReductionData,tblFullReductionTitle);
        this.dataTableFullReduction.validate();
        this.dataTableFullReduction.repaint();
    }


    public FrmSys(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("饱了么外卖管理平台");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //菜单
        this.menu_merchant.add(this.menuItem_showMerchant); this.menuItem_showMerchant.addActionListener(this);
        this.menu_merchant.add(this.menuItem_addMerchant); this.menuItem_addMerchant.addActionListener(this);
        this.menu_merchant.add(this.menuItem_deleteMerchant); this.menuItem_deleteMerchant.addActionListener(this);
        this.menu_merchant.add(this.menuItem_modifyMerchant); this.menuItem_modifyMerchant.addActionListener(this);
        this.menu_product.add(this.menuItem_addProductType); this.menuItem_addProductType.addActionListener(this);
        this.menu_product.add(this.menuItem_deleteProductType); this.menuItem_deleteProductType.addActionListener(this);
        this.menu_product.add(this.menuItem_modifyProductType); this.menuItem_modifyProductType.addActionListener(this);
        this.menu_product.add(this.menuItem_addProduct); this.menuItem_addProduct.addActionListener(this);
        this.menu_product.add(this.menuItem_deleteProduct); this.menuItem_deleteProduct.addActionListener(this);
        this.menu_product.add(this.menuItem_modifyProduct); this.menuItem_modifyProduct.addActionListener(this);
        this.menu_fullReduction.add(this.menuItem_addFullReduction); this.menuItem_addFullReduction.addActionListener(this);
        this.menu_fullReduction.add(this.menuItem_deleteFullReduction); this.menuItem_deleteFullReduction.addActionListener(this);
        this.menu_fullReduction.add(this.menuItem_modifyFullReduction); this.menuItem_modifyFullReduction.addActionListener(this);
        this.menu_discountCoupon.add(this.menuItem_addDiscountCoupon); this.menuItem_addDiscountCoupon.addActionListener(this);
        this.menu_discountCoupon.add(this.menuItem_deleteDiscountCoupon); this.menuItem_deleteDiscountCoupon.addActionListener(this);
        this.menu_discountCoupon.add(this.menuItem_modifyDiscountCoupon); this.menuItem_modifyDiscountCoupon.addActionListener(this);

        menubar.add(menu_merchant);
        menubar.add(menu_product);
        menubar.add(menu_fullReduction);
        menubar.add(menu_discountCoupon);
        this.setJMenuBar(menubar);



        //添加商品列
        this.getContentPane().add(new JScrollPane(this.dataTableMerchant),BorderLayout.WEST);
        this.dataTableMerchant.addMouseListener(new MouseAdapter (){

            @Override
            public void mouseClicked(MouseEvent e) {
                int i=FrmSys.this.dataTableMerchant.getSelectedRow();
                if(i<0) {
                    return;
                }
                FrmSys.this.reloadProductTypeTable(i); //更新选中的商家行的产品类别
                FrmSys.this.reloadFullReduction(i);  //更新选中的商家行的满减方案
            }

        });
        //添加商品类别列
        this.getContentPane().add(new JScrollPane(this.dataTableProductType), BorderLayout.CENTER);
        //添加满减方案列
//        activityBar.setLayout(new BorderLayout());
//        activityBar.setSize(100,100);
        activityBar.add(new JScrollPane(this.dataTableFullReduction), BorderLayout.WEST);
        this.getContentPane().add(activityBar, BorderLayout.NORTH);

        this.dataTableProductType.addMouseListener(new MouseAdapter (){

            @Override
            public void mouseClicked(MouseEvent e) {
                int i=FrmSys.this.dataTableProductType.getSelectedRow();
                if(i<0) {
                    return;
                }
                FrmSys.this.reloadProductDetailsTable(i); //更新选中的产品类别行
            }

        });
        //添加商品列
        this.getContentPane().add(new JScrollPane(this.dataTableProductDetails), BorderLayout.EAST);

        this.reloadMerchantTable();




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menuItem_addMerchant) {
            FrmAddMerchant dlg = new FrmAddMerchant(this, "添加商家", true);
            dlg.setVisible(true);
            reloadMerchantTable();
        } else if (e.getSource() == this.menuItem_modifyMerchant) {
            if (this.curMerchant == null) {
                JOptionPane.showMessageDialog(null, "请选择商家", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyMerchant dlg = new FrmModifyMerchant(this, "修改商家信息", true);
            dlg.merchant = curMerchant;
            dlg.setVisible(true);
            reloadMerchantTable();
        } else if (e.getSource() == this.menuItem_deleteMerchant) {
            if (this.curMerchant == null) {
                JOptionPane.showMessageDialog(null, "请选择商家", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.merchantManager.deleteMerchant(this.curMerchant);
                JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                reloadMerchantTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == this.menuItem_addProductType) {
            if (this.curMerchant == null) {
                JOptionPane.showMessageDialog(null, "请选择商家", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmAddProductType dlg = new FrmAddProductType(this, "添加产品类别", true);
            dlg.merchant = curMerchant;
            dlg.setVisible(true);
            int i = FrmSys.this.dataTableMerchant.getSelectedRow();
            if (i < 0) {
                return;
            }
            FrmSys.this.reloadProductTypeTable(i);
            reloadMerchantTable();
        } else if (e.getSource() == this.menuItem_modifyProductType) {
            if (this.curProductType == null) {
                JOptionPane.showMessageDialog(null, "请选择产品类别", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyProductType dlg = new FrmModifyProductType(this, "修改产品类别", true);
            dlg.productType = curProductType;
            dlg.setVisible(true);
            int i = FrmSys.this.dataTableMerchant.getSelectedRow();
            if (i < 0) {
                return;
            }
            FrmSys.this.reloadProductTypeTable(i);
            reloadMerchantTable();
        } else if (e.getSource() == this.menuItem_deleteProductType) {
            if (this.curProductType == null) {
                JOptionPane.showMessageDialog(null, "请选择产品类别", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.productTypeManager.deleteProductType(this.curProductType);
                JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                int i = FrmSys.this.dataTableMerchant.getSelectedRow();
                if (i < 0) {
                    return;
                }
                FrmSys.this.reloadProductTypeTable(i);
                reloadMerchantTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == this.menuItem_addProduct) {
            if (this.curProductType == null) {
                JOptionPane.showMessageDialog(null, "请选择商品类别", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmAddProduct dlg = new FrmAddProduct(this, "添加商品", true);
            dlg.productType = curProductType;
            dlg.setVisible(true);
            int i = FrmSys.this.dataTableProductDetails.getSelectedRow();
            if (i < 0) {
                return;
            }
            FrmSys.this.reloadProductDetailsTable(i);
            int j = FrmSys.this.dataTableProductType.getSelectedRow();
            if (j < 0) {
                return;
            }
            FrmSys.this.reloadProductTypeTable(j);
//            reloadMerchantTable();
        } else if (e.getSource() == this.menuItem_deleteProduct) {
            int i=FrmSys.this.dataTableProductDetails.getSelectedRow();
            if (i<0) {
                JOptionPane.showMessageDialog(null, "请选择产品", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.productDetailsManager.deleteProduct(this.productDetails.get(i));
                JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                FrmSys.this.reloadProductDetailsTable(i);
                int j = FrmSys.this.dataTableProductType.getSelectedRow();
                if (j < 0) {
                    return;
                }
                FrmSys.this.reloadProductTypeTable(j);
//                reloadMerchantTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }else if (e.getSource() == this.menuItem_modifyProduct) {
            int i=FrmSys.this.dataTableProductDetails.getSelectedRow();
            if (i<0) {
                JOptionPane.showMessageDialog(null, "请选择产品", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

                FrmModifyProductDetails dlg=new FrmModifyProductDetails(this, "修改商品", true);
                dlg.productDetails=productDetails.get(i);
                dlg.setVisible(true);
                FrmSys.this.reloadProductDetailsTable(i);
                int j = FrmSys.this.dataTableProductType.getSelectedRow();
                if (j < 0) {
                    return;
                }
                FrmSys.this.reloadProductTypeTable(j);
//                reloadMerchantTable();
        }else if (e.getSource() == this.menuItem_addProductType) {
            if (this.curMerchant == null) {
                JOptionPane.showMessageDialog(null, "请选择商家", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmAddFullReduction dlg = new FrmAddFullReduction(this, "添加满减信息", true);
            dlg.merchant = curMerchant;
            dlg.setVisible(true);
            int i = FrmSys.this.dataTableMerchant.getSelectedRow();
            if (i < 0) {
                return;
            }
            reloadMerchantTable();
        }
    }
}