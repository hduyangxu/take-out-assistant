package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FrmConfirm extends JDialog implements ActionListener {
    public BeanMerchant merchant=null;
    public List<BeanOrderDetail> shoppingCart = null;
    public List<BeanFullReduction> fullReduction = null;
    public List<BeanAddress> address = null;

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelSumPrice = new JLabel("总价：");
    private JLabel labelVipPrice = new JLabel("会员价：");
    private JLabel labelFullReduction = new JLabel("当前满减");
    private JLabel labelDiscountCoupon = new JLabel("选择优惠券：");
    private JLabel labelFinalPrice = new JLabel("最终价格:");
    private JLabel labelAddress = new JLabel("选择地址：");

    private JLabel edtSumPrice = new JLabel();
    private JLabel edtVipPrice = new JLabel();
    private JLabel edtFinalPrice = new JLabel();
    private JLabel edtFullReduction = new JLabel();
    private JComboBox<String> edtDiscountCoupon = new JComboBox<>();
    private JComboBox<String> edtAddress = new JComboBox<>();

    private float fullReductionRequest=-1;
    private float fullReductionMoney=-1;
    float sumPrice=0;
    float vipPrice=0;
    float sumDiscount=0;

    private void acquireFullReduction() throws DbException {//获取满减信息
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select fullReduction_request,fullReduction_Money from tbl_fullReduction where merchant_id = ? group by fullReduction_request DESC";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                if(sumPrice>rs.getFloat(1)){
                    fullReductionRequest=rs.getFloat(1);
                    fullReductionMoney=rs.getFloat(2);
                    break;
                }
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
    private void acquireDiscountCoupon() throws DbException {//获取满减信息
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select fullReduction_request,fullReduction_Money from tbl_fullReduction where merchant_id = ? group by fullReduction_request DESC";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                if(sumPrice>rs.getFloat(1)){
                    fullReductionRequest=rs.getFloat(1);
                    fullReductionMoney=rs.getFloat(2);
                    break;
                }
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }


    public FrmConfirm(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

//        for(int i = 0; i < shoppingCart.size(); i++){
//            sumPrice+=shoppingCart.get(i).getProduct_sumPrice();
//            vipPrice+=shoppingCart.get(i).getProduct_discountPrice();
//        }
//        edtSumPrice.setText(String.valueOf(sumPrice));
//        edtVipPrice.setText(String.valueOf(vipPrice));

        try {
            acquireFullReduction();
        } catch (DbException e) {
            e.printStackTrace();
        }

        if(fullReductionRequest<0){
            edtFullReduction.setText("无");
        }else{
            edtFullReduction.setText("满"+fullReductionRequest+"减"+fullReductionMoney);
            sumDiscount=fullReductionMoney;
        }


        workPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        workPane.add(labelSumPrice);
        workPane.add(edtSumPrice);
        workPane.add(labelVipPrice);
        workPane.add(edtVipPrice);
        workPane.add(labelFullReduction);
        workPane.add(edtFullReduction);
//        workPane.add(labelDetail);
//        workPane.add(edtDetail);
//        workPane.add(labelPeople);
//        workPane.add(edtPeople);
//        workPane.add(labelTel);
//        workPane.add(edtTel);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(250, 360);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel)
            this.setVisible(false);
//        else if(e.getSource()==this.btnOk){
//            try {
//                PersonPlanUtil.addressManager.modifyAddress(address,new String(edtProvince.getText()),
//                        new String(edtCity.getText()),new String(edtZone.getText()),new String(edtDetail.getText()),
//                        new String(edtPeople.getText()), new String(edtTel.getText()));
//                JOptionPane.showMessageDialog(null, "修改成功", "成功",JOptionPane.INFORMATION_MESSAGE);
//                this.setVisible(false);
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//        }
    }

}
