package App.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import App.entity.User;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import App.service.SqlKit;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

public class MainUI {

    private static User user = new User();
    private static int loingState = 0;
    private static JFrame frame = new JFrame();
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenu menu = new JMenu();
    private static JMenuItem menuItem_1 = new JMenuItem();
    private static JMenuItem menuItem_2 = new JMenuItem();

    private static JTabbedPane tabbedPane = new JTabbedPane();
    private static JPanel userInfoPanel = new JPanel();//用户信息界面
    private static JLabel userInfoUid = new JLabel();
    private static JLabel userInfoUidT = new JLabel();
    private static JLabel userInfoName = new JLabel();
    private static JTextField userInfoNameT = new JTextField();
    private static JLabel userInfoSex = new JLabel();
    private static JPanel userInfoSexPanel = new JPanel();
    private static ButtonGroup buttonGroup = new ButtonGroup();
    private static JRadioButton sex1 = new JRadioButton();
    private static JRadioButton sex2 = new JRadioButton();
    private static JLabel userInfoUserName = new JLabel();
    private static JTextField userInfoUserNameT = new JTextField();
    private static JLabel userInfoEmail = new JLabel();
    private static JTextField userInfoEmailT = new JTextField();
    private static JLabel userInfoPhone = new JLabel();
    private static JTextField userInfoPhoneT = new JTextField();
    private static JButton modify = new JButton();




    private JPanel verifyPanel = new JPanel();//用户审核界面

    private JPanel applyPanel = new JPanel();//用户申请界面



    /**
     * 主界面UI
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


        frame.add(tabbedPane);

        loadSearchTab();
//        tabbedPane.add("申请审核", verifyPanel);
//        tabbedPane.add("用户申请",applyPanel);


    }

    public static void setLoingState(int loingStateate, User tmpuser){
        loingState = loingStateate;
        user = tmpuser;
        loadUserInfoTab();

        System.out.println("登陆状态：" + loingState);
    }
    private static JPanel searchQueryPanel = new JPanel();//用户查询界面
    private static JPanel searchQueryPanelButton = new JPanel();//用户查询按钮界面
    private static JButton searchClubButton = new JButton();//社团查询按钮
    private static JButton searchUserButton = new JButton();//用户查询按钮
    private static JButton searchActivityButton = new JButton();//活动查询按钮
    private static JLabel searchClubLabel = new JLabel();//社团名称
    private static JTextField searchClubText = new JTextField();//社团模糊查询文本框
    private static JScrollPane searchScrollPane = new JScrollPane();//查询结果显示界面

    private static JTable resultTable = new JTable();//显示表格
    private static DefaultTableModel model1 = new DefaultTableModel();

    private static void loadSearchTab(){
        tabbedPane.add("查询信息", searchQueryPanel);
        /*
        searchQueryPanel.setLayout(new BorderLayout());

        searchQueryPanel.add(searchQueryPanelButton, BorderLayout.NORTH);
        //searchQueryPanelButton.setLayout(null);
        searchQueryPanelButton.setBorder(new EmptyBorder(0,0,0,0));


        //resultTable.setRowHeight(30);
        //searchQueryPanel.add(searchScrollPane);

        searchQueryPanel.add(searchScrollPane, BorderLayout.CENTER);

        //searchQueryPanel.add(searchScrollPane);
        //searchScrollPane.setBorder(new EmptyBorder(100,0,0,0));
        */
        searchQueryPanel.setLayout(null);
        //searchQueryPanel.setBounds(0,0,200,100);
        searchQueryPanel.add(searchQueryPanelButton);
        searchQueryPanelButton.setLayout(null);
        searchQueryPanelButton.setBounds(0,0,800,90);
        searchQueryPanel.add(searchScrollPane);
        searchScrollPane.setBounds(0,100,800,300);




        searchClubButton.setText("社团查询");
        searchQueryPanelButton.add(searchClubButton);
        searchClubButton.setBounds(0,0,100,28);
        searchClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model1 = new SqlKit().searchClub();
                resultTable.setModel(model1);
                searchScrollPane.add(resultTable);
                searchScrollPane.setViewportView(resultTable);
            }
        });

        searchActivityButton.setText("活动查询");
        searchActivityButton.setBounds(110,0,100,28);
        searchQueryPanelButton.add(searchActivityButton);
        searchActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    private static void loadUserInfoTab(){
        tabbedPane.add("用户信息",userInfoPanel);
        userInfoPanel.setLayout(null);

        userInfoPanel.add(userInfoUid);
        userInfoUid.setText("用户ID: ");
        userInfoUid.setBounds(0,0,60,22);
        userInfoPanel.add(userInfoUidT);
        userInfoUidT.setText(user.getUid());
        userInfoUidT.setBounds(60, 0,90,22);

        userInfoPanel.add(userInfoName);
        userInfoName.setText("姓名： ");
        userInfoName.setBounds(0,30,60,22);
        userInfoPanel.add(userInfoNameT);
        userInfoNameT.setText(user.getUserName());
        userInfoNameT.setBounds(60, 30,150,22);

        userInfoPanel.add(userInfoSex);
        userInfoSex.setText("性别： ");
        userInfoSex.setBounds(0,60,60,22);
        buttonGroup.add(sex1);
        buttonGroup.add(sex2);
        sex1.setText("男");
        sex2.setText("女");
        userInfoSexPanel.add(sex1);
        userInfoSexPanel.add(sex2);
        userInfoSexPanel.setLayout(new FlowLayout((FlowLayout.LEFT)));
        userInfoSexPanel.setBounds(60, 53,150,30);
        userInfoPanel.add(userInfoSexPanel);
        if(user.getSex().equals("男")){
            sex1.setSelected(true);
        }else{
            sex2.setSelected(true);
        }



        userInfoPanel.add(userInfoUserName);
        userInfoUserName.setText("用户名： ");
        userInfoUserName.setBounds(0,90,60,22);
        userInfoPanel.add(userInfoUserNameT);
        userInfoUserNameT.setText(user.getName());
        userInfoUserNameT.setBounds(60, 90,150,22);

        userInfoPanel.add(userInfoEmail);
        userInfoEmail.setText("邮箱： ");
        userInfoEmail.setBounds(0,120,60,22);
        userInfoPanel.add(userInfoEmailT);
        userInfoEmailT.setText(user.getEmail());
        userInfoEmailT.setBounds(60, 120,150,22);

        userInfoPanel.add(userInfoPhone);
        userInfoPhone.setText("手机： ");
        userInfoPhone.setBounds(0,150,60,22);
        userInfoPanel.add(userInfoPhoneT);
        userInfoPhoneT.setText(user.getPhone());
        userInfoPhoneT.setBounds(60, 150,150,22);


        modify.setText("编辑");
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyInfoUI modifyInfoUI = new ModifyInfoUI(user);
                modifyInfoUI.setModal(true);
                modifyInfoUI.setLocationRelativeTo(frame);
                modifyInfoUI.setVisible(true);
            }
        });
        userInfoPanel.add(modify);
        modify.setBounds(60, 180,150,22);
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
