package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanFullReduction;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModifyFullReduction extends JDialog implements ActionListener {
    public BeanFullReduction fullReduction = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelRequest = new JLabel("满减要求：");
    private JLabel labelMoney = new JLabel("满减金额：");
    private JLabel labelConflict = new JLabel("是否可叠加：");

    private JTextField edtRequest = new JTextField(20);
    private JTextField edtMoney = new JTextField(20);
    private JRadioButton conflict = new JRadioButton();
    private JRadioButton notConflict = new JRadioButton();
    private ButtonGroup isConflict = new ButtonGroup();

    public FrmModifyFullReduction(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        isConflict.add(conflict);
        isConflict.add(notConflict);
        notConflict.setSelected(true);
        workPane.setLayout(new FlowLayout((FlowLayout.CENTER)));
        workPane.add(labelRequest);
        workPane.add(edtRequest);
        workPane.add(labelMoney);
        workPane.add(edtMoney);
        workPane.add(labelConflict);
        workPane.add(conflict);
        workPane.add(notConflict);
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
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }
        else if(e.getSource()==this.btnOk){
            float fullReduction_request=Float.parseFloat(this.edtRequest.getText());
            float fullReduction_money=Float.parseFloat(this.edtMoney.getText());
            String fullReduction_isConflict = null;
            if(conflict.isSelected()){
                fullReduction_isConflict = conflict.getText();
            }else {
                fullReduction_isConflict = notConflict.getText();
            }
            try {
                PersonPlanUtil.fullReductionManager.modifyReduction(fullReduction,fullReduction_request,fullReduction_money,fullReduction_isConflict);
                JOptionPane.showMessageDialog(null, "修改成功", "成功",JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    }
}
