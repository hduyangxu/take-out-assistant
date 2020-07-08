package cn.edu.zucc.personplan.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.*;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JPanel imagePane = new JPanel();
	private JButton btnLogin = new JButton("登录");
	private JButton btnRegister = new JButton("注册");
	private JButton btnSysLogin = new JButton("管理员登录");
	private JLabel lIcon = new JLabel();
	private JLabel labelUser = new JLabel("用户：");
	private JLabel labelPwd = new JLabel("密码：");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);

	public void setEdtPwd(JPasswordField edtPwd) {
		this.edtPwd.setEchoChar('*');
	}

	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnSysLogin);
		toolBar.setLayout(new GridLayout(1,3,5,0));
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		this.setEdtPwd(edtPwd);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		Icon icon = new ImageIcon("src/icon.png");
		lIcon.setIcon(icon);
        imagePane.add(lIcon);
		imagePane.setBackground(Color.white);
        this.getContentPane().add(imagePane,BorderLayout.NORTH);

		this.setSize(320, 360);
		// 设置窗口居中
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
        this.setResizable(false);
		btnLogin.addActionListener(this);
		btnSysLogin.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin) {
			String user_name = this.edtUserId.getText();
			String password = new String(this.edtPwd.getPassword());
			try {
				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(user_name, password);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "登录失败",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmMain.userType = 1;
			this.setVisible(false);

		} else if(e.getSource()==this.btnRegister){
			FrmRegister dlg=new FrmRegister(this,"饱了么注册",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnSysLogin){
				FrmSysLogin dlg1 = new FrmSysLogin(null,"管理员登陆",true);
				dlg1.setVisible(true);
			    this.setVisible(false);
		}

	}

}
