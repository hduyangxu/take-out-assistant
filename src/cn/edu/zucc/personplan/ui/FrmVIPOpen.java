package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanDiscountCoupon;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmVIPOpen  extends JDialog implements ActionListener {
    public BeanDiscountCoupon discountCoupon = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelTime = new JLabel("开通一个月");
    private JLabel labelCost = new JLabel("价格：");
    private JLabel edtCost = new JLabel("15元");


    public FrmVIPOpen(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.setLayout(new FlowLayout((FlowLayout.CENTER)));
        workPane.add(labelTime);
        labelTime.setFont(new Font("宋体",Font.PLAIN,21));
        labelTime.setForeground(Color.RED);
        workPane.add(labelCost);
        workPane.add(edtCost);
        this.getContentPane().add(workPane, BorderLayout.CENTER);

        this.setSize(150, 150);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }else if(e.getSource()==this.btnOk){
            try {
                PersonPlanUtil.userManager.vipManager();
                JOptionPane.showMessageDialog(null, "开通/续费成功", "成功",JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } catch (BaseException baseException) {
                baseException.printStackTrace();
            }
        }
    }
}
