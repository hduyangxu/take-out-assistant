package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanDiscountCoupon;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrmModifyDiscountCoupon extends JDialog implements ActionListener {
    public BeanDiscountCoupon discountCoupon = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelMoney = new JLabel("优惠金额：");
    private JLabel labelStartDate = new JLabel("起始日期：");
    private JLabel labelEndDate = new JLabel("结束日期：");
    private JLabel labelRequest = new JLabel("需要订单数");

    private JTextField edtMoney = new JTextField(20);
    private JTextField edtStartDate = new JTextField(20);
    private JTextField edtEndDate = new JTextField(20);
    private JTextField edtRequest = new JTextField(20);


    public FrmModifyDiscountCoupon(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.setLayout(new FlowLayout((FlowLayout.CENTER)));
        workPane.add(labelMoney);
        workPane.add(edtMoney);
        workPane.add(labelStartDate);
        workPane.add(edtStartDate);
        workPane.add(labelEndDate);
        workPane.add(edtEndDate);
        workPane.add(labelRequest);
        workPane.add(edtRequest);
        this.getContentPane().add(workPane, BorderLayout.CENTER);

        this.setSize(250, 300);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }
        else if(e.getSource()==this.btnOk){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
            Date start_date=null,end_date=null;
            try {
                start_date=sdf.parse(edtStartDate.getText());
                end_date=sdf.parse(edtEndDate.getText());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            float money=Float.parseFloat(this.edtMoney.getText());
            int request=Integer.parseInt(this.edtRequest.getText());
            try {
                PersonPlanUtil.discountCouponManager.modifyDiscountCoupon(discountCoupon,money,start_date,end_date,request);
                JOptionPane.showMessageDialog(null, "修改成功", "成功",JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    }
}
