package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
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

public class FrmConfirm extends JDialog implements ActionListener {
    public BeanMerchant merchant = FrmUser.curMerchant;
    public List<BeanOrderDetail> shoppingCart = FrmUser.orderDetail;
    public List<BeanAddress> address = FrmUser.address;


    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelSumPrice = new JLabel("总价：");
    private JLabel labelVipPrice = new JLabel("会员价：");
    private JLabel labelFullReduction = new JLabel("当前满减：");
    private JCheckBox userFullReduction = new JCheckBox("不享受满减");
    private JLabel labelAddress = new JLabel("选择地址：");
    private List<Integer> address_id = new ArrayList<>();
    public static int finalAddress = 0;

    private JLabel edtSumPrice = new JLabel();
    private JLabel edtVipPrice = new JLabel();
    private JLabel edtFullReduction = new JLabel();
    private JComboBox<String> edtAddress = new JComboBox<>();

    private float fullReductionRequest=-1;
    private float fullReductionMoney=-1;
    public static int fullReductionId=0;
    public static float sumPrice=0;
    public static float vipPrice=0;
    public static float sumDiscount=0;
    public static String isUser="否";

    private void acquireFullReduction() throws DbException {//获取满减信息
        if (merchant==null) try {
            throw new BusinessException("未选择商家！");
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn= DBUtil2.getConnection();
            String sql = "select fullReduction_request,fullReduction_Money,fullReduction_id from tbl_fullReduction where merchant_id = ? group by fullReduction_request DESC";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,merchant.getMerchant_id());
            java.sql.ResultSet rs= pst.executeQuery();
            while(rs.next()){
                if(sumPrice>rs.getFloat(1)){
                    fullReductionRequest=rs.getFloat(1);
                    fullReductionMoney=rs.getFloat(2);
                    fullReductionId=rs.getInt(3);
                    break;
                }
            }
            if(fullReductionRequest<0){
                edtFullReduction.setText("无");
            }else{
                edtFullReduction.setText("满"+fullReductionRequest+"减"+fullReductionMoney);
                sumDiscount=fullReductionMoney;
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


    private void acquireAddress() throws DbException {//获取优惠券信息
        edtAddress.addItem("---请选择地址—--");
        for(int i=0;i<address.size();i++){
            BeanAddress curAddress=address.get(i);
            address_id.add(curAddress.getAddress_id());
            edtAddress.addItem("联系人:"+curAddress.getAddress_people()+" 电话："+curAddress.getAddress_tel()
            +" 详细地址:"+curAddress.getAddress_detail());
        }
    }
    public FrmConfirm(Frame f, String s, boolean b) {
        super(f, s, b);
        fullReductionId=0;
        sumPrice=0;
        vipPrice=0;
        sumDiscount=0;
        isUser="否";

        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        for(int i = 0; i < shoppingCart.size(); i++){
            sumPrice+=shoppingCart.get(i).getProduct_sumPrice();
            vipPrice+=shoppingCart.get(i).getProduct_discountPrice();
        }
        edtSumPrice.setText(String.valueOf(sumPrice));
        edtVipPrice.setText(String.valueOf(vipPrice));
        try {
            acquireFullReduction();
            acquireAddress();
        } catch (DbException e) {
            e.printStackTrace();
        }

        userFullReduction.addItemListener(new ItemListener() {//更新满减
            @Override
            public void itemStateChanged(ItemEvent e) {
                    if(userFullReduction.isSelected()){
                        isUser="是";
                        edtFullReduction.setText("--不使用满减--");
                        sumDiscount=0;
                    }else{
                        isUser="否";
                        try {
                            acquireFullReduction();
                        } catch (DbException dbException) {
                            dbException.printStackTrace();
                        }
                    }
            }
        });

        edtAddress.addItemListener(new ItemListener() {//更新地址
            @Override
            public void itemStateChanged(ItemEvent e) {
                    finalAddress=address_id.get(edtAddress.getSelectedIndex()-1);
            }
        });


        workPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        workPane.add(labelSumPrice);
        workPane.add(edtSumPrice);
        edtSumPrice.setForeground(Color.RED);
        workPane.add(labelVipPrice);
        workPane.add(edtVipPrice);
        edtVipPrice.setForeground(Color.RED);
        workPane.add(labelFullReduction);
        workPane.add(edtFullReduction);
        edtFullReduction.setForeground(Color.RED);
        workPane.add(userFullReduction);
        workPane.add(labelAddress);
        workPane.add(edtAddress);
        this.getContentPane().add(workPane, BorderLayout.CENTER);

        this.setSize(450, 200);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel){
            this.setVisible(false);
        }
        else if(e.getSource()==this.btnOk){
            if(finalAddress==0){
                JOptionPane.showMessageDialog(null, "必须选择地址", "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmFinalConfirm frameFinalConfirm = new FrmFinalConfirm(null,"最终确认",true);
            frameFinalConfirm.setVisible(true);
        }
    }


}
