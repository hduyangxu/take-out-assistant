package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanProductEvaluate;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmEvaluateDetails extends JDialog implements ActionListener {
    public BeanProductEvaluate productEvaluate = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelStarRated = new JLabel("----------请您对商品进行打分----------");
    private JLabel labelContent = new JLabel("请给出评价内容：");

    private JRadioButton edtOne = new JRadioButton("1");
    private JRadioButton edtTwo = new JRadioButton("2");
    private JRadioButton edtThree = new JRadioButton("3");
    private JRadioButton edtFour = new JRadioButton("4");
    private JRadioButton edtFive = new JRadioButton("5");
    private ButtonGroup starRated = new ButtonGroup();
    private JTextField edtContent = new JTextField(20);

    public FrmEvaluateDetails(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        starRated.add(edtOne);
        starRated.add(edtTwo);
        starRated.add(edtThree);
        starRated.add(edtFour);
        starRated.add(edtFive);
        edtFive.setSelected(true);
        workPane.add(labelStarRated);
        workPane.add(edtOne);
        workPane.add(edtTwo);
        workPane.add(edtThree);
        workPane.add(edtFour);
        workPane.add(edtFive);
        workPane.add(labelContent);
        workPane.add(edtContent);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(250, 250);

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
            int star=5;
            if(edtFour.isSelected()) star=4;
            else if(edtThree.isSelected()) star=3;
            else if(edtTwo.isSelected()) star=2;
            else if(edtOne.isSelected()) star=1;
            try {
                PersonPlanUtil.productDetailsManager.evaluateProduct(productEvaluate,edtContent.getText(),star);
                JOptionPane.showMessageDialog(null, "评价成功", "成功",JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
