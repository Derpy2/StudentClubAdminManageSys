package App.UI;

import javax.swing.*;

import App.entity.Club;
import App.entity.User;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

import App.service.SqlKit;
import App.entity.Config;

public class EstablishClubUI extends JDialog {

    JPanel jPanel = new JPanel();
    JLabel clubName = new JLabel();
    JTextField clubNameT = new JTextField();
    JLabel reason = new JLabel();
    JTextArea reasonT = new JTextArea();
    JButton comfirm = new JButton();
    JButton cancel = new JButton();


    String cid = "";
    String date;
    Date tmpdate = new Date();
    java.sql.Date localDate = new java.sql.Date(tmpdate.getTime());
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    Club club = new Club();

    EstablishClubUI(String uid){
        this.setTitle("社团申请");
        this.add(jPanel);
        setBounds(100,100, 400,400);
        jPanel.setLayout(null);
        jPanel.setBounds(0,0,400,500);

        jPanel.add(clubName);
        clubName.setText("社团名称：");
        clubName.setBounds(80,10,70,22);
        jPanel.add(clubNameT);
        clubNameT.setBounds(160,10,120,22);


        jPanel.add(reason);
        reason.setText("申请理由:");
        reason.setBounds(80,40,70,22);
        jPanel.add(reasonT);
        reasonT.setBounds(160,40,120,100);

        jPanel.add(comfirm);
        comfirm.setText("确定");
        comfirm.setBounds(100,180,70,30);
        comfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clubNameT.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"请输入社团名");
                    return;
                }
                //date = simpleDateFormat.format(localDate);
                int tot = new SqlKit().countClub();
                tot += 1;
                for(int i = 0;i < 10;i++){
                    cid = Config.number.charAt(tot % 10) + cid;
                    tot /= 10;
                }

                club.setCid(cid);
                club.setCName(clubNameT.getText());
                club.setUid(uid);
                club.setcTime(localDate);
                club.setcReason(reasonT.getText());

                new SqlKit().applyClub(club);
            }
        });

        jPanel.add(cancel);
        cancel.setText("取消");
        cancel.setBounds(180,180,70,30);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposePanel();
            }
        });





    }
    private void disposePanel(){
        this.dispose();
    }
    public static void main(String[] args){
        try{
            EstablishClubUI establishClubUI = new EstablishClubUI("U000000003");
            establishClubUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            establishClubUI.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
