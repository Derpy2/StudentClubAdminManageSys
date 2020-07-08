package App.UI;

import javax.swing.*;
import java.awt.event.*;
import App.entity.User;

public class MainUI {

    private User user = new User();

    private JFrame frame = new JFrame();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu();
    private JMenuItem menuItem_1 = new JMenuItem();
    private JMenuItem menuItem_2 = new JMenuItem();

    /**
     *
     */
    MainUI(){
        init();
    }
    public void init(){
        frame.setTitle("主界面");
        frame.setBounds(100,100,800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //菜单条
        frame.setJMenuBar(menuBar);

        //菜单-系统管理
        menuBar.add(menu);
        menu.setText("\u7cfb\u7edf\u7ba1\u7406");

        //登陆
        menu.add(menuItem_1);
        menuItem_1.setText("\u767b\u9646");
        menuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginUI loginUI = new LoginUI();
                loginUI.setModal(true);
                loginUI.setLocationRelativeTo(frame);
                loginUI.setVisible(true);
                user = loginUI.getUser();
                if(user.getState() == 1){
                    loginUI.dispose();
                }
            }
        });

        //退出
        menu.add(menuItem_2);
        menuItem_2.setText("\u9000\u51fa");
        menuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setState(0);
                System.exit(0);
            }
        });
    }

    public static void main(String[] args){
        try{
            MainUI window = new MainUI();
            window.frame.setLocationRelativeTo(null);
            window.frame.setVisible(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
