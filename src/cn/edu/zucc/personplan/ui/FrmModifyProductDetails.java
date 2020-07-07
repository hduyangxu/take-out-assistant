package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanProductDetails;
import cn.edu.zucc.personplan.model.BeanProductType;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModifyProductDetails extends JDialog implements ActionListener {
    public BeanProductDetails productDetails = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelName = new JLabel("名称：");
    private JLabel labelPrice = new JLabel("价格：");
    private JLabel labelDiscountPrice = new JLabel("优惠价：");
    private JTextField edtName = new JTextField(20);
    private JTextField edtPrice = new JTextField(20);
    private JTextField edtDiscountPrice = new JTextField(20);

    public FrmModifyProductDetails(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.setLayout(new FlowLayout((FlowLayout.CENTER)));
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelPrice);
        workPane.add(edtPrice);
        workPane.add(labelDiscountPrice);
        workPane.add(edtDiscountPrice);
        this.getContentPane().add(workPane, BorderLayout.CENTER);

        this.setSize(250, 250);
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
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            return;
        } else if (e.getSource() == this.btnOk) {
            String name=this.edtName.getText();
            float price= Float.parseFloat(this.edtPrice.getText());
            float discountPrice= Float.parseFloat(this.edtDiscountPrice.getText());
            try {
                PersonPlanUtil.productDetailsManager.modifyProductDetails(productDetails, name, price, discountPrice);
                JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    }
}

