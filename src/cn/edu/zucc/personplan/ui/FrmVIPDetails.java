package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.model.BeanDiscountCoupon;
import cn.edu.zucc.personplan.model.BeanUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmVIPDetails extends JDialog implements ActionListener {
    public BeanDiscountCoupon discountCoupon = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("续费");
    private JButton btnCancel = new JButton("确定");
    private JLabel labelState = new JLabel("当前身份：");
    private JLabel labelDate = new JLabel("到期时间：");
    private JLabel edtState = new JLabel();
    private JLabel edtDate = new JLabel();

    public void refreshState(){
        if(BeanUser.currentLoginUser.getUser_isVIP()==1){
            edtState.setText("至尊会员");
            edtState.setForeground(Color.RED);
            edtState.setFont(new Font("宋体" , Font.BOLD , 20));
            edtDate.setText(String.valueOf(BeanUser.currentLoginUser.getUser_vipEndDate()));
        }else if(BeanUser.currentLoginUser.getUser_isVIP()==0){
            edtState.setText("普通用户");
            edtDate.setText("赶快开通VIP，享受优惠价格吧，每年最高省8000元！");
            edtDate.setForeground(Color.RED);
            btnOk.setText("开通会员");
        }else if(BeanUser.currentLoginUser.getUser_isVIP()==-1){
            edtState.setText("会员已过期");
            edtDate.setText("您的会员已过期，续费继续享受优惠价吧！");
            edtDate.setForeground(Color.RED);
        }
    }

    public FrmVIPDetails(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        refreshState();

        workPane.setLayout(new FlowLayout((FlowLayout.CENTER)));
        workPane.add(labelState);
        workPane.add(edtState);
        workPane.add(labelDate);
        workPane.add(edtDate);
        this.getContentPane().add(workPane, BorderLayout.CENTER);

        this.setSize(350, 150);
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
        }
        if(e.getSource()==this.btnOk){
            FrmVIPOpen frameVipOpen = new FrmVIPOpen(null,"开通/续费会员",true);
            frameVipOpen.setVisible(true);
            refreshState();
        }
    }
}
