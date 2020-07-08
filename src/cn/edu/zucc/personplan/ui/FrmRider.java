package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanMerchant;
import cn.edu.zucc.personplan.model.BeanProductType;
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

public class FrmRider extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar=new JMenuBar();
    private JButton button_addRider=new JButton("添加骑手");
    private JButton button_deleteRider=new JButton("删除骑手");
    private JButton button_modifyRider=new JButton("修改骑手");

    private Object tblRiderTitles[]= BeanRider.tableTitles;
    private Object tblRiderData[][];
    DefaultTableModel tabRiderModel=new DefaultTableModel();
    private JTable dataTableRider=new JTable(tabRiderModel);

    private Object tblRiderAccountTitles[]= BeanRiderAccount.tableTitles;
    private Object tblRiderAccountData[][];
    DefaultTableModel tabRiderAccountModel=new DefaultTableModel();
    private JTable dataTableRiderAccount=new JTable(tabRiderAccountModel);

    private BeanRider curRider=null;

    List<BeanRider> allRider=null;
    List<BeanRiderAccount> riderAccount=null;

    private void reloadRiderTable(){//重新载入骑手信息
        try {
            allRider=PersonPlanUtil.riderManager.loadAllRider();
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

    private void reloadRiderAccountTable(int riderIdx){//重新载入骑手入账表信息
        if(riderIdx<0) return;
        curRider=allRider.get(riderIdx);
        try {
            riderAccount=PersonPlanUtil.riderAccountManager.loadRiderAccount(curRider);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblRiderAccountData=new Object[riderAccount.size()][BeanRider.tableTitles.length];
        for(int i=0;i<riderAccount.size();i++){
            for(int j=0;j<BeanRiderAccount.tableTitles.length;j++)
                tblRiderAccountData[i][j]=riderAccount.get(i).getCell(j);
        }
        tabRiderAccountModel.setDataVector(tblRiderAccountData,tblRiderAccountTitles);
        this.dataTableRiderAccount.validate();
        this.dataTableRiderAccount.repaint();
    }

    public FrmRider(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("饱了么外卖管理平台");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.button_addRider.addActionListener(this);
        this.button_deleteRider.addActionListener(this);
        this.button_modifyRider.addActionListener(this);
        menubar.add(button_addRider);
        menubar.add(button_deleteRider);
        menubar.add(button_modifyRider);
        this.setJMenuBar(menubar);

        JScrollPane js6=new JScrollPane(this.dataTableRider);
        js6.setPreferredSize(new Dimension(500,10));
        JScrollPane js7=new JScrollPane(this.dataTableRiderAccount);
        js7.setPreferredSize(new Dimension(500,10));

        //添加骑手列
        this.getContentPane().add(js6, BorderLayout.WEST);
        this.dataTableRider.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                int i=FrmRider.this.dataTableRider.getSelectedRow();
                if(i<0) {
                    return;
                }
                FrmRider.this.reloadRiderAccountTable(i); //更新选中的商家行的产品类别
            }

        });
        this.getContentPane().add(js7,BorderLayout.CENTER);


        this.reloadRiderTable();


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.button_addRider) {
            FrmAddRider dlg = new FrmAddRider(this, "添加骑手", true);
            dlg.setVisible(true);
            reloadRiderTable();
        }else if (e.getSource() == this.button_modifyRider) {
            if (this.curRider == null) {
                JOptionPane.showMessageDialog(null, "请选择骑手", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyRider dlg = new FrmModifyRider(this, "修改骑手信息", true);
            dlg.rider = curRider;
            dlg.setVisible(true);
            reloadRiderTable();
        } else if (e.getSource() == this.button_deleteRider) {
            if (this.curRider == null) {
                JOptionPane.showMessageDialog(null, "请选择骑手", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.riderManager.deleteRider(this.curRider);
                JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                reloadRiderTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
