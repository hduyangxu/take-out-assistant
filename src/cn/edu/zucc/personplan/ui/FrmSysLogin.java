package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.ui.FrmMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static cn.edu.zucc.personplan.ui.FrmMain.userType;

public class FrmSysLogin extends JDialog implements ActionListener {
       public FrmLogin fatherJf = null;
       private JPanel toolBar = new JPanel();
       private JPanel workPane = new JPanel();
       private JPanel imagePane = new JPanel();
       private JButton btnOk = new JButton("登录");
       private JButton btnCancel = new JButton("取消");
       private JLabel labelUser = new JLabel("账号：");
       private JLabel labelPwd = new JLabel("密码：");
       private JLabel lIcon = new JLabel();
       private JTextField edtUserSysName = new JTextField(20);
       private JPasswordField edtSysPwd = new JPasswordField(20);

       public FrmSysLogin(Frame f, String s, boolean b) {
           super(f, s, b);
           toolBar.add(this.btnOk);
           toolBar.add(btnCancel);
           toolBar.setLayout(new GridLayout(1, 2, 5, 0));
           this.getContentPane().add(toolBar, BorderLayout.SOUTH);
           Icon icon = new ImageIcon("src/admin.jpg");
           lIcon.setIcon(icon);
           imagePane.add(lIcon);
           imagePane.setBackground(Color.white);
           this.getContentPane().add(imagePane, BorderLayout.NORTH);
           workPane.setLayout(new FlowLayout(FlowLayout.LEFT));
           workPane.add(labelUser);
           workPane.add(edtUserSysName);
           workPane.add(labelPwd);
           workPane.add(edtSysPwd);
           this.getContentPane().add(workPane, BorderLayout.CENTER);
           this.setSize(260, 380);
           double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
           double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
           this.setLocation((int) (width - this.getWidth()) / 2,
                   (int) (height - this.getHeight()) / 2);
           this.validate();
           this.setResizable(false);
           this.btnCancel.addActionListener(this);
           this.btnOk.addActionListener(this);
           this.setDefaultCloseOperation(HIDE_ON_CLOSE);
       }

       @Override
       public void actionPerformed(ActionEvent e) {
           if (e.getSource() == this.btnCancel) {
               System.exit(0);
               this.setVisible(false);
           }
           else if (e.getSource() == this.btnOk) {
               String sysUserName = this.edtUserSysName.getText();
               String sysUserPassword = new String(this.edtSysPwd.getPassword());
               try {
                   PersonPlanUtil.userManager.systemUserLogin(sysUserName, sysUserPassword);
                   FrmSys frameSys = new FrmSys();
                   frameSys.setVisible(true);
                   this.setVisible(false);
                   fatherJf.setVisible(false);
               } catch (BaseException e1) {
                   JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                   return;
               }
           }


       }
   }


