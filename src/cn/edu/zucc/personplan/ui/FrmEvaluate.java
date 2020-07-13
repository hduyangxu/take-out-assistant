package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanProductOrder;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import com.sun.deploy.panel.JreDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmEvaluate extends JDialog implements ActionListener {
    public BeanProductOrder productOrder = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelRider = new JLabel("对骑手评价：");
    private JRadioButton edtGood = new JRadioButton("好评");
    private JRadioButton edtBad = new JRadioButton("差评");
    private ButtonGroup group = new ButtonGroup();
    public FrmEvaluate(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        group.add(edtGood);
        group.add(edtBad);
        edtGood.setSelected(true);
        workPane.add(labelRider);
        workPane.add(edtGood);
        workPane.add(edtBad);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(150, 170);

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
            String evaluate=null;
            if(edtGood.isSelected()){
                evaluate=edtGood.getText();
            }else{
                evaluate=edtBad.getText();
            }
            try {
                PersonPlanUtil.riderAccountManager.evaluateRider(productOrder, evaluate);
                JOptionPane.showMessageDialog(null, "评价成功", "成功",JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
                PersonPlanUtil.orderManager.confirmArrive(productOrder);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

}
