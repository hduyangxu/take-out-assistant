package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmAddStep extends JDialog implements ActionListener {
	public BeanPlan plan=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("计划步骤名称：");
	private JLabel labelPlanStartDate = new JLabel("计划开始日期：");
	private JLabel labelPlanFinishDate = new JLabel("计划完成日期：");
	
	private JTextField edtPlanStartDate = new JTextField(20);
	private JTextField edtPlanFinishDate = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	public FrmAddStep(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelPlanStartDate);
		workPane.add(edtPlanStartDate);
		workPane.add(labelPlanFinishDate);
		workPane.add(edtPlanFinishDate);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(380, 180);
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
			String name=this.edtName.getText();
			String sd=this.edtPlanStartDate.getText();
			String fd=this.edtPlanFinishDate.getText();
			try {
				PersonPlanUtil.stepManager.add(plan,name,sd,fd);
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "添加成功", "成功",JOptionPane.INFORMATION_MESSAGE);
				
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
	
}
