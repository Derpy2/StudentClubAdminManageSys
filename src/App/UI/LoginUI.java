package App.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import App.service.userLoginCheck;
import App.entity.User;
import App.UI.MainUI;


public class LoginUI extends JDialog{
    private final JPanel loginPanel = new JPanel();
    private JTextField userName = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JLabel userNameLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();
    private JPanel buttonPanel = new JPanel();
    private JButton loginIn = new JButton();
    private JButton signUp = new JButton();
    public int isLogin = 0;
    public User user = new User();

    /**
     * 登陆UI
     * 包含登陆的用户名和密码的标签和输入框
     * 下方有登陆按钮和注册按钮
     */
    LoginUI(){
        this.setTitle("\u767b\u9646");
        setBounds(100,100, 400, 300);
        getContentPane().setLayout(new BorderLayout());
        loginPanel.setBorder(new EmptyBorder(10,10,10,10));
        getContentPane().add(loginPanel, BorderLayout.CENTER);
        loginPanel.setLayout(null);

        userNameLabel.setText("\u7528\u6237\u540d\uff1a");//用户名
        userNameLabel.setBounds(65,47,56,16);
        loginPanel.add(userNameLabel);

        passwordLabel.setText("\u5BC6\u7801\uFF1A");//密码
        passwordLabel.setBounds(65,93,56,16);
        loginPanel.add(passwordLabel);

        userName.setBounds(126,44,116,22);//用户名输入
        loginPanel.add(userName);
        userName.setColumns(20);
        password.setBounds(126,90,116,22);//密码输入
        password.setColumns(20);
        loginPanel.add(password);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        loginIn.setText("\u767b\u9646");//登陆
        getRootPane().setDefaultButton(loginIn);
        loginIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User tmpUser = new User(userName.getText().trim(), String.valueOf(password.getPassword()).trim());
                if(new userLoginCheck().LoginCheck(tmpUser)){
                    userName.setText("");
                    password.setText("");
                    tmpUser.setState(1);
                    tmpUser = new userLoginCheck().getUserInfo(tmpUser);
                    user = tmpUser;
                    isLogin = 1;
                    MainUI.setLoingState(isLogin, tmpUser);
                    JOptionPane.showMessageDialog(null, "登陆成功");
                    loginUIDispose();
                }else{
                    JOptionPane.showMessageDialog(null, "用户名或密码错误");
                    userName.setText("");
                    password.setText("");
                    userName.requestFocus();
                }
            }
        });
        buttonPanel.add(loginIn);


        signUp.setText("\u6ce8\u518c");//注册
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpUI signUpUI = new SignUpUI();
                signUpUI.setModal(true);
                signUpUI.setLocationRelativeTo(getContentPane());
                signUpUI.setVisible(true);
            }
        });
        buttonPanel.add(signUp);
    }

    public User getUser(){
        return user;
    }
    private void loginUIDisModal(){
        this.setModal(false);
    }
    private void loginUIDispose(){
        this.dispose();
    }
    public static void main(String[] args){
        try {
            LoginUI LUI = new LoginUI();
            LUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            LUI.setVisible(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
