package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanAddress;
import cn.edu.zucc.personplan.model.BeanDiscountCoupon;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModifyAddress extends JDialog implements ActionListener {
    public BeanAddress address = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelProvince = new JLabel("省份：");
    private JLabel labelCity = new JLabel("城市：");
    private JLabel labelZone = new JLabel("区/县：");
    private JLabel labelDetail = new JLabel("详细地址：");
    private JLabel labelPeople = new JLabel("联系人：");
    private JLabel labelTel = new JLabel("电话：");
    private JTextField edtProvince = new JTextField(20);
    private JTextField edtCity = new JTextField(20);
    private JTextField edtZone = new JTextField(20);
    private JTextField edtDetail = new JTextField(20);
    private JTextField edtPeople = new JTextField(20);
    private JTextField edtTel = new JTextField(20);
    public FrmModifyAddress(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        workPane.add(labelProvince);
        workPane.add(edtProvince);
        workPane.add(labelCity);
        workPane.add(edtCity);
        workPane.add(labelZone);
        workPane.add(edtZone);
        workPane.add(labelDetail);
        workPane.add(edtDetail);
        workPane.add(labelPeople);
        workPane.add(edtPeople);
        workPane.add(labelTel);
        workPane.add(edtTel);
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
        else if(e.getSource()==this.btnOk){
            try {
                PersonPlanUtil.addressManager.modifyAddress(address,new String(edtProvince.getText()),
                        new String(edtCity.getText()),new String(edtZone.getText()),new String(edtDetail.getText()),
                        new String(edtPeople.getText()), new String(edtTel.getText()));
                JOptionPane.showMessageDialog(null, "修改成功", "成功",JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
