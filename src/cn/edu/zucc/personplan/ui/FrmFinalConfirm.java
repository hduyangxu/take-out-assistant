package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmFinalConfirm extends JDialog implements ActionListener {
    private String isUser=FrmConfirm.isUser;
    private BeanMerchant merchant=FrmUser.curMerchant;//
    private List<Float> discountCoupon_money=new ArrayList<>();
    private List<Integer> discountCoupon_id=new ArrayList<>();
    private int finalDiscountCouponId=0;//
    private int finalFullReductionId=FrmConfirm.fullReductionId;

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelDiscountCoupon=new JLabel("选择优惠券:");
    private JComboBox<String> edtDiscountCoupon = new JComboBox<>();

    private JLabel labelFinalPrice=new JLabel("最终价格：");
    private JLabel edtFinalPrice=new JLabel();

    private float curDiscount=0;
    private float finalDiscount=FrmConfirm.sumDiscount;
    private float finalPrice=BeanUser.currentLoginUser.getUser_isVIP()==1 ? FrmConfirm.vipPrice:FrmConfirm.sumPrice;//
    private int finalAddress=FrmConfirm.finalAddress;//

    private void acquireDiscountCoupon1() throws DbException {//获取优惠券信息
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql ="select a.discountCoupon_money,discountCoupon_count,a.discountCoupon_id " +
                    "from tbl_userDiscountCoupon a,tbl_discountcoupon b " +
                    "where a.discountCoupon_id=b.discountCoupon_id " +
                    "and b.merchant_id = ? " +
                    "and b.discountCoupon_isConflict=? "+
                    "and user_id = ? group by a.discountCoupon_money";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            pst.setString(2,isUser);
            pst.setInt(3, BeanUser.currentLoginUser.getUser_id());
            java.sql.ResultSet rs= pst.executeQuery();
            edtDiscountCoupon.addItem("---下拉选择---");
            while(rs.next()){
                discountCoupon_money.add(rs.getFloat(1));
                discountCoupon_id.add(rs.getInt(3));
                edtDiscountCoupon.addItem("优惠金额："+rs.getFloat(1)+" 拥有数量："+rs.getInt(2));
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
    private void acquireDiscountCoupon2() throws DbException {//获取优惠券信息
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql ="select a.discountCoupon_money,discountCoupon_count,a.discountCoupon_id " +
                    "from tbl_userDiscountCoupon a,tbl_discountcoupon b " +
                    "where a.discountCoupon_id=b.discountCoupon_id " +
                    "and b.merchant_id = ? " +
                    "and user_id = ? group by a.discountCoupon_money";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            pst.setInt(2, BeanUser.currentLoginUser.getUser_id());
            java.sql.ResultSet rs= pst.executeQuery();
            edtDiscountCoupon.addItem("---下拉选择---");
            while(rs.next()){
                discountCoupon_money.add(rs.getFloat(1));
                discountCoupon_id.add(rs.getInt(3));
                edtDiscountCoupon.addItem("优惠金额："+rs.getFloat(1)+" 拥有数量："+rs.getInt(2));
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
    public FrmFinalConfirm(Frame f, String s, boolean b){
        super(f, s, b);
        if(isUser.equals("是")){
            try {
                acquireDiscountCoupon2();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }else{
            try {
                acquireDiscountCoupon1();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        edtFinalPrice.setText(String.valueOf(finalPrice-finalDiscount-curDiscount));
        edtDiscountCoupon.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                curDiscount=discountCoupon_money.get(edtDiscountCoupon.getSelectedIndex()-1);
                finalDiscountCouponId=discountCoupon_id.get(edtDiscountCoupon.getSelectedIndex()-1);
                edtFinalPrice.setText(String.valueOf(finalPrice-finalDiscount-curDiscount));
            }
        });

        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        workPane.add(labelDiscountCoupon);
        workPane.add(edtDiscountCoupon);
        workPane.add(labelFinalPrice);
        workPane.add(edtFinalPrice);
        edtFinalPrice.setForeground(Color.RED);
        this.getContentPane().add(workPane, BorderLayout.CENTER);

        this.setSize(300, 200);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel){
            this.setVisible(false);
        }
    }
}
