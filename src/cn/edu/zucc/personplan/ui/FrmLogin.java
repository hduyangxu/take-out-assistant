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
	private JButton btnLogin = new JButton("µÇÂ½");
	private JButton btnRegister = new JButton("×¢²á");
	private JLabel lIcon = new JLabel();
	private JLabel labelUser = new JLabel("ÓÃ»§£º");
	private JLabel labelPwd = new JLabel("ÃÜÂë£º");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);

	public void setEdtPwd(JPasswordField edtPwd) {
		this.edtPwd.setEchoChar('±¥');
	}

	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.setLayout(new GridLayout(1,2,5,0));
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
		// ÆÁÄ»¾ÓÖÐÏÔÊ¾
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
        this.setResizable(false);
		btnLogin.addActionListener(this);
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
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);

		} else if(e.getSource()==this.btnRegister){
			FrmRegister dlg=new FrmRegister(this,"ÓÃ»§×¢²á",true);
			dlg.setVisible(true);
		}
	}

}
