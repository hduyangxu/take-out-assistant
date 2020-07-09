package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class FrmMain extends JFrame implements ActionListener {
	public static int userType = 0;
	private static final long serialVersionUID = 1L;
	FrmLogin frameLogin= null;


	public FrmMain(){
		frameLogin=new FrmLogin(null,"饱了么登录窗口",true);
		frameLogin.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
