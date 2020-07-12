package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanAllProductOrder;
import cn.edu.zucc.personplan.model.BeanProductOrder;
import cn.edu.zucc.personplan.model.BeanRider;
import cn.edu.zucc.personplan.model.BeanRiderAccount;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmDistributeRider  extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar=new JMenuBar();
    private JButton button_confirmDistribute=new JButton("确认分配");

    private Object tblRiderTitles[]= BeanRider.tableTitles;
    private Object tblRiderData[][];
    DefaultTableModel tabRiderModel=new DefaultTableModel();
    private JTable dataTableRider=new JTable(tabRiderModel);

    public BeanAllProductOrder productOrder = null;

    List<BeanRider> allRider=null;

    private void reloadRiderTable(){//重新载入骑手信息
        try {
            allRider= PersonPlanUtil.riderManager.loadAllRider();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblRiderData=new Object[allRider.size()][BeanRider.tableTitles.length];
        for(int i=0;i<allRider.size();i++){
            for(int j=0;j<BeanRider.tableTitles.length;j++)
                tblRiderData[i][j]=allRider.get(i).getCell(j);
        }
        tabRiderModel.setDataVector(tblRiderData,tblRiderTitles);
        this.dataTableRider.validate();
        this.dataTableRider.repaint();
    }


    public FrmDistributeRider(Frame f, String s, boolean b){
        super(f,s,b);
        this.button_confirmDistribute.addActionListener(this);
        menubar.setLayout(new FlowLayout(FlowLayout.CENTER));
        menubar.add(button_confirmDistribute);
        this.setJMenuBar(menubar);

        JScrollPane jsDistributeRider=new JScrollPane(this.dataTableRider);
        this.getContentPane().add(jsDistributeRider);
        this.reloadRiderTable();

        this.setSize(300, 400);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.button_confirmDistribute) {
            int i=FrmDistributeRider.this.dataTableRider.getSelectedRow();
            if(i<0) {
                JOptionPane.showMessageDialog(null, "请选择骑手", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.orderManager.distributeRider(productOrder,allRider.get(i));
                JOptionPane.showMessageDialog(null, "分配成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (BaseException baseException) {
                baseException.printStackTrace();
            }
            this.setVisible(false);
        }
    }
}
