package App.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import App.entity.User;
import App.entity.Config;
import App.service.SqlKit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinClubUI extends JDialog {

    JPanel jPanel = new JPanel();

    JButton joinButton = new JButton();
    JButton showButton = new JButton();

    JLabel reason = new JLabel();
    JTextArea reasonText = new JTextArea();

    JTable jTable = new JTable();
    JScrollPane jScrollPane = new JScrollPane();
    DefaultTableModel model;

    JoinClubUI(User user){
        this.setTitle("社团加入");
        setBounds(100,100,820,500);
        //setLayout(null);
        this.add(jPanel);
        jPanel.setLayout(null);

        jPanel.add(jScrollPane);
        jScrollPane.setBounds(130,0,680,500);


        jPanel.add(joinButton);
        joinButton.setText("加入社团");
        joinButton.setBounds(10,20 ,100,30);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos = jTable.getSelectedRow();
                if(pos != -1){
                    String cid = String.valueOf(jTable.getValueAt(pos, 0));
                    System.out.println(cid);
                    SqlKit sqlKit = new SqlKit();
                    sqlKit.joinClub(user.getUid(), cid, reasonText.getText());
                    JOptionPane.showMessageDialog(null,"申请成功");
                }else{
                    JOptionPane.showMessageDialog(null,"请选择社团");
                }

            }
        });

        jPanel.add(showButton);
        showButton.setText("显示社团");
        showButton.setBounds(10,60,100,30);
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model = new SqlKit().searchClub("");
                jTable.setModel(model);
                jScrollPane.add(jTable);
                jScrollPane.setViewportView(jTable);
            }
        });

        jPanel.add(reason);
        reason.setText("申请理由:");
        reason.setBounds(10,90,100,30);

        jPanel.add(reasonText);
        reasonText.setBounds(10,120,100,150);
        reasonText.setLineWrap(true);


    }


    public static void main(String[] args){
        try {
            JoinClubUI joinClubUI = new JoinClubUI(new Config().basicUser);
            joinClubUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            joinClubUI.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
