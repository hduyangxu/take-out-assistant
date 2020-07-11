package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class FrmUser extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_user=new JMenu("账号管理");
    private JMenu menu_address=new JMenu("地址管理");
    private JMenu menu_shoppingCart=new JMenu("购物车管理");
    private JMenu menu_other=new JMenu("其他管理");

    private JMenuItem  menuItem_modifyUserName=new JMenuItem("修改用户名");
    private JMenuItem  menuItem_modifyPwd=new JMenuItem("修改密码");
    private JMenuItem  menuItem_addAddress=new JMenuItem("添加地址");
    private JMenuItem  menuItem_modifyAddress=new JMenuItem("修改地址");
    private JMenuItem  menuItem_deleteAddress=new JMenuItem("删除地址");
    private JMenuItem  menuItem_AddToCart=new JMenuItem("添加到购物车");
    private JMenuItem  menuItem_RemoveFromCart=new JMenuItem("从购物车中移除");
    private JMenuItem  menuItem_confirmOrder=new JMenuItem("确认下单");
    private JMenuItem  menuItem_order=new JMenuItem("订单管理");
    private JMenuItem  menuItem_vipInfo=new JMenuItem("会员信息");
    private JMenuItem  menuItem_discountCoupon=new JMenuItem("查看优惠券");
    private JMenuItem  menuItem_discountCouponRequest=new JMenuItem("查看集单数");
    private JPanel activityBar = new JPanel();

    private JPanel statusBar = new JPanel();

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

    private Object tblDiscountCouponTitle[]= BeanDiscountCoupon.tableTitles;
    private Object tblDiscountCouponData[][];
    DefaultTableModel tabDiscountCouponModel=new DefaultTableModel();
    private JTable dataTableDiscountCoupon=new JTable(tabDiscountCouponModel);

    private Object tblAddressTitle[]= BeanAddress.tableTitles;
    private Object tblAddressData[][];
    DefaultTableModel tabAddressModel=new DefaultTableModel();
    private JTable dataTableAddress=new JTable(tabAddressModel);

    private Object tblShoppingCartTitle[]= BeanOrderDetail.tableTitles;
    private Object tblShoppingCartData[][];
    DefaultTableModel tabShoppingCartModel=new DefaultTableModel();
    private JTable dataTableShoppingCart=new JTable(tabShoppingCartModel);

    public static BeanMerchant curMerchant=null;
    private BeanProductType curProductType=null;

    List<BeanMerchant> allMerchant=null;
    List<BeanProductType> productType=null;
    List<BeanProductDetails> productDetails=null;
    public static List<BeanFullReduction> fullReduction=null;
    List<BeanDiscountCoupon> discountCoupon=null;
    public static List<BeanAddress> address=null;
    public static List<BeanOrderDetail> orderDetail=null;


    private void reloadMerchantTable(){//重新载入商家信息
        try {
            allMerchant= PersonPlanUtil.merchantManager.loadAllMerchant();
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

    private void reloadAddress(){//载入地址信息
        if(BeanUser.currentLoginUser==null) try {
            throw new BusinessException("当前用户不存在");
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        try {
            address= PersonPlanUtil.addressManager.loadAddress(BeanUser.currentLoginUser);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblAddressData=new Object[address.size()][BeanAddress.tableTitles.length];
        for(int i=0;i<address.size();i++){
            for(int j=0;j<BeanAddress.tableTitles.length;j++)
                tblAddressData[i][j]=address.get(i).getCell(j);
        }
        tabAddressModel.setDataVector(tblAddressData,tblAddressTitle);
        this.dataTableAddress.validate();
        this.dataTableAddress.repaint();
    }

    private void reloadShoppingCartTable(int merchantIdx){
        if(merchantIdx<0) return;
        curMerchant=allMerchant.get(merchantIdx);
        try {
            orderDetail=PersonPlanUtil.shoppingCartManager.loadShoppingCart(curMerchant);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblShoppingCartData = new Object[orderDetail.size()][BeanOrderDetail.tableTitles.length];
        for(int i=0;i<orderDetail.size();i++){
            for(int j=0;j<BeanOrderDetail.tableTitles.length;j++)
                tblShoppingCartData[i][j]=orderDetail.get(i).getCell(j);
        }
        tabShoppingCartModel.setDataVector(tblShoppingCartData,tblShoppingCartTitle);
        this.dataTableShoppingCart.validate();
        this.dataTableShoppingCart.repaint();
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
        if(productTypeIdx<0) {
            while(tabProductDetailsModel.getRowCount()>0){
                tabProductDetailsModel.removeRow(tabProductDetailsModel.getRowCount()-1);
            }
            return ;
        }
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

    private void reloadDiscountCoupon(int merchantIdx){
        if(merchantIdx<0) return;
        curMerchant=allMerchant.get(merchantIdx);
        try {
            discountCoupon=PersonPlanUtil.discountCouponManager.loadDiscountCoupon(curMerchant);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblDiscountCouponData=new Object[discountCoupon.size()][BeanDiscountCoupon.tableTitles.length];
        for(int i=0;i<discountCoupon.size();i++){
            for(int j=0;j<BeanDiscountCoupon.tableTitles.length;j++)
                tblDiscountCouponData[i][j]=discountCoupon.get(i).getCell(j);
        }

        tabDiscountCouponModel.setDataVector(tblDiscountCouponData,tblDiscountCouponTitle);
        this.dataTableDiscountCoupon.validate();
        this.dataTableDiscountCoupon.repaint();
    }



    public FrmUser(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("饱了么用户点餐系统");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menu_user.add(this.menuItem_modifyUserName); this.menuItem_modifyUserName.addActionListener(this);
        this.menu_user.add(this.menuItem_modifyPwd); this.menuItem_modifyPwd.addActionListener(this);
        this.menu_address.add(this.menuItem_addAddress); this.menuItem_addAddress.addActionListener(this);
        this.menu_address.add(this.menuItem_modifyAddress); this.menuItem_modifyAddress.addActionListener(this);
        this.menu_address.add(this.menuItem_deleteAddress); this.menuItem_deleteAddress.addActionListener(this);
        this.menu_shoppingCart.add(this.menuItem_AddToCart); this.menuItem_AddToCart.addActionListener(this);
        this.menu_shoppingCart.add(this.menuItem_RemoveFromCart); this.menuItem_RemoveFromCart.addActionListener(this);
        this.menu_shoppingCart.add(this.menuItem_confirmOrder); this.menuItem_confirmOrder.addActionListener(this);
        this.menu_other.add(this.menuItem_order); this.menuItem_order.addActionListener(this);
        this.menu_other.add(this.menuItem_vipInfo); this.menuItem_vipInfo.addActionListener(this);
        this.menu_other.add(this.menuItem_discountCoupon); this.menuItem_discountCoupon.addActionListener(this);
        this.menu_other.add(this.menuItem_discountCouponRequest); this.menuItem_discountCouponRequest.addActionListener(this);

        menubar.add(menu_user);
        menubar.add(menu_address);
        menubar.add(menu_shoppingCart);
        menubar.add(menu_other);
        this.setJMenuBar(menubar);

        JScrollPane js1=new JScrollPane(this.dataTableMerchant);
        js1.setPreferredSize(new Dimension(400,400));
        JScrollPane js2=new JScrollPane(this.dataTableProductType);
        js2.setPreferredSize(new Dimension(200,400));
        JScrollPane js3=new JScrollPane(this.dataTableProductDetails);
        js3.setPreferredSize(new Dimension(600,400));
        JScrollPane js4=new JScrollPane(this.dataTableFullReduction);
        js4.setPreferredSize(new Dimension(200,100));
        JScrollPane js8=new JScrollPane(this.dataTableAddress);
        js8.setPreferredSize(new Dimension(500,100));
        JScrollPane js9=new JScrollPane(this.dataTableShoppingCart);
        js9.setPreferredSize(new Dimension(500,100));

        this.getContentPane().add(js1,BorderLayout.WEST);
        this.dataTableMerchant.addMouseListener(new MouseAdapter (){

            @Override
            public void mouseClicked(MouseEvent e) {
                int i=FrmUser.this.dataTableMerchant.getSelectedRow();
                if(i<0) {
                    return;
                }
                FrmUser.this.reloadProductTypeTable(i); //更新选中的商家行的产品类别
                FrmUser.this.reloadFullReduction(i);  //更新选中的商家行的满减方案
                FrmUser.this.reloadShoppingCartTable(i);
                FrmUser.this.reloadProductDetailsTable(-1); //清空商品信息
            }

        });
        //添加商品类别列
        this.getContentPane().add(js2, BorderLayout.CENTER);
        //添加满减方案列
        activityBar.add(js4,BorderLayout.WEST);
        activityBar.add(js8,BorderLayout.CENTER);
        activityBar.add(js9,BorderLayout.EAST);
        this.getContentPane().add(activityBar, BorderLayout.NORTH);

        this.dataTableProductType.addMouseListener(new MouseAdapter (){

            @Override
            public void mouseClicked(MouseEvent e) {
                int i=FrmUser.this.dataTableProductType.getSelectedRow();
                if(i<0) {
                    return;
                }
                FrmUser.this.reloadProductDetailsTable(i); //更新选中的产品类别行
            }

        });
        //添加商品列
        this.getContentPane().add(js3, BorderLayout.EAST);
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("您好! "+BeanUser.currentLoginUser.getUser_name());//修改成   您好！+登陆用户名
        statusBar.add(label);
        this.getContentPane().add(statusBar,BorderLayout.SOUTH);
        this.reloadMerchantTable();
        this.reloadAddress();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menuItem_modifyUserName) {
            if (BeanUser.currentLoginUser == null) {
                JOptionPane.showMessageDialog(null, "用户不存在", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyName dlg = new FrmModifyName(this, "修改用户名", true);
            dlg.setVisible(true);
            reloadMerchantTable();
        } else if (e.getSource() == this.menuItem_modifyPwd) {
            if (BeanUser.currentLoginUser == null) {
                JOptionPane.showMessageDialog(null, "用户不存在", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyPwd dlg = new FrmModifyPwd(this, "修改密码", true);
            dlg.setVisible(true);
            reloadMerchantTable();
        } else if (e.getSource() == this.menuItem_addAddress) {
            if (BeanUser.currentLoginUser == null) {
                JOptionPane.showMessageDialog(null, "用户不存在", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmAddAddress dlg = new FrmAddAddress(this, "添加地址", true);
            dlg.setVisible(true);
            reloadAddress();
        }else if(e.getSource() == this.menuItem_modifyAddress){
            int i=FrmUser.this.dataTableAddress.getSelectedRow();
            if (i<0) {
                JOptionPane.showMessageDialog(null, "请选择地址", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyAddress dlg = new FrmModifyAddress(this, "修改地址信息", true);
            dlg.address = address.get(i);
            dlg.setVisible(true);
            reloadAddress();
        }else if(e.getSource() == this.menuItem_deleteAddress){
            int i=FrmUser.this.dataTableAddress.getSelectedRow();
            if (i<0) {
                JOptionPane.showMessageDialog(null, "请选择地址", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.addressManager.deleteAddress(this.address.get(i));
                JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                FrmUser.this.reloadAddress();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }else if(e.getSource() == this.menuItem_AddToCart){
            int i=FrmUser.this.dataTableProductDetails.getSelectedRow();
            if (i<0) {
                JOptionPane.showMessageDialog(null, "请选择商品", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmAddToShoppingCart dlg=new FrmAddToShoppingCart(this, "添加商品", true);
            dlg.productDetails=productDetails.get(i);
            dlg.setVisible(true);
            int j = FrmUser.this.dataTableMerchant.getSelectedRow();
            if (j < 0) {
                return;
            }
            FrmUser.this.reloadProductTypeTable(j);
            reloadMerchantTable();
        }else if(e.getSource() == this.menuItem_RemoveFromCart){
            int i=FrmUser.this.dataTableShoppingCart.getSelectedRow();
            if (i<0) {
                JOptionPane.showMessageDialog(null, "请选择商品", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.shoppingCartManager.removeFromShoppingCart(this.orderDetail.get(i));
                JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                int j=FrmUser.this.dataTableShoppingCart.getSelectedRow();
                FrmUser.this.reloadShoppingCartTable(j);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }else if(e.getSource() == this.menuItem_discountCoupon){
            FrmShowDiscountCoupon frameShowDiscountCoupon = new FrmShowDiscountCoupon();
            frameShowDiscountCoupon.setVisible(true);
        }else if(e.getSource() == this.menuItem_confirmOrder){
            int i = FrmUser.this.dataTableMerchant.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmConfirm frameConfirm = new FrmConfirm(null,"订单确认",true);
            frameConfirm.setVisible(true);
            FrmUser.this.reloadShoppingCartTable(i);
        }else if(e.getSource()==this.menuItem_vipInfo){
            FrmVIPDetails frameVipInfo=new FrmVIPDetails(this,"会员信息",true);
            frameVipInfo.setVisible(true);
        }else if(e.getSource()==this.menuItem_discountCouponRequest){
            FrmShowDiscountCouponRequest frameShowDiscountCouponRequest=new FrmShowDiscountCouponRequest();
            frameShowDiscountCouponRequest.setVisible(true);
        }
    }
}