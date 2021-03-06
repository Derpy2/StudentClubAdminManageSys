package App.UI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import App.entity.User;
import App.service.inputCheck;
import App.service.SqlKit;
import jdk.nashorn.internal.scripts.JD;

public class SignUpUI extends JDialog{
    private final JPanel signUpPanel = new JPanel();
    private JTextField userName = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JPasswordField password2 = new JPasswordField();
    private JRadioButton sex1 = new JRadioButton();
    private JRadioButton sex2 = new JRadioButton();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel butPanel = new JPanel();
    private JTextField nameField = new JTextField();
    private JTextField emailfield = new JTextField();
    private JTextField phoneField = new JTextField();

    private JLabel userNameLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();
    private JLabel passwordLabel2 = new JLabel();
    private JLabel sex = new JLabel();
    private JLabel name = new JLabel();
    private JLabel email = new JLabel();
    private JLabel phone = new JLabel();
    private JLabel hint = new JLabel();
    private JPanel buttonPanel = new JPanel();

    private JButton signUp = new JButton();

    /**
     * 注册界面
     * 必须输入用户名，密码，重复密码，性别，姓名，邮箱才可注册
     * 电话可不填
     * 需要重复输入密码来确认密码
     */
    SignUpUI(){
        this.setTitle("\u6ce8\u518c");
        setBounds(100,100, 350, 600);
        getContentPane().setLayout(new BorderLayout());
        signUpPanel.setBorder(new EmptyBorder(10,10,10,10));
        getContentPane().add(signUpPanel, BorderLayout.CENTER);
        signUpPanel.setLayout(null);

        userNameLabel.setText("\u7528\u6237\u540d\uff1a");//用户名
        userNameLabel.setBounds(65,44,70,16);
        signUpPanel.add(userNameLabel);

        passwordLabel.setText("\u5BC6\u7801\uFF1A");//密码
        passwordLabel.setBounds(65,90,70,16);
        signUpPanel.add(passwordLabel);

        passwordLabel2.setText("重复密码：");
        passwordLabel2.setBounds(65,136,70,16);
        signUpPanel.add(passwordLabel2);

        name.setText("姓名:");
        name.setBounds(65,182,70,16);
        signUpPanel.add(name);

        sex.setText("性别：");
        sex.setBounds(65,228,70,16);
        signUpPanel.add(sex);

        email.setText("邮箱：");
        email.setBounds(65,274,70,16);
        signUpPanel.add(email);

        phone.setText("电话：");
        phone.setBounds(65,320,70,16);
        signUpPanel.add(phone);

        userName.setBounds(126,44,116,22);//用户名输入
        signUpPanel.add(userName);
        userName.setColumns(20);

        password.setBounds(126,90,116,22);//密码输入
        password.setColumns(20);
        signUpPanel.add(password);

        password2.setBounds(126,136,116,22);//密码二次输入
        password2.setColumns(20);
        signUpPanel.add(password2);

        nameField.setBounds(126,182,116,22);
        nameField.setColumns(20);
        signUpPanel.add(nameField);

        buttonGroup.add(sex1);//性别单选按钮
        buttonGroup.add(sex2);
        sex1.setText("男");
        sex2.setText("女");
        butPanel.add(sex1);
        butPanel.add(sex2);
        sex1.setSelected(true);
        butPanel.setLayout(new FlowLayout((FlowLayout.LEFT)));
        butPanel.setBounds(126,220,116,30);
        signUpPanel.add(butPanel);


        emailfield.setBounds(126,274,116,22);//邮箱
        emailfield.setColumns(20);
        signUpPanel.add(emailfield);

        phoneField.setBounds(126,320,116,22);//电话
        phoneField.setColumns(20);
        signUpPanel.add(phoneField);

//        hint.setText("* 名字 2<=4个字符\n" +
//                "* 用户名 1<=16个字符\n" +
//                "* 密码 6 < 16位 只能包含_， 数字，字母\n" +
//                "* 邮箱为*****@****.**格式，最大30位\n" +
//                "* phone 11位 可为空\n" +
//                "* sex不为空");

        hint.setText("<html><HTML><body>* 名字 2<=4个字符 <br>" +
                " * 用户名 1<=16个字符<br>" +
                "* 密码 6 < 16位 只能包含_, 数字, 字母<br>" +
                "* 邮箱为*****@****.**格式，最大30位<br>" +
                "* phone 11位 可为空<br>" +
                "* sex不为空</body></html>");
        hint.setBounds(65,330,250,200);
        signUpPanel.add(hint);


        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);



        signUp.setText("\u6ce8\u518c");//注册
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd2 = String.valueOf(password2.getPassword());
                String phone = emailfield.getText();
                if(phone.equals("")){
                    phone = null;
                }
                User tmpuser = new User(userName.getText(), String.valueOf(password.getPassword()), nameField.getText(), sex1.isSelected() ? "男" : "女", phoneField.getText(), phone);
                if(pwd2.equals(tmpuser.getPassword()) && inputCheck.checkSignUp(tmpuser) && new SqlKit().UserNameCheck(tmpuser.getUserName())){
                    SqlKit sql1 = new SqlKit();
                    int num = sql1.countUUser(tmpuser);
                    sql1.insertUser(tmpuser, num + 1);
                    JOptionPane.showMessageDialog(null, "注册成功");
                    SignUpUIDispose();
                }else{
                    JOptionPane.showMessageDialog(null, "请检查输入数据格式是否正确");
                }

            }
        });
        buttonPanel.add(signUp);
    }

    private void SignUpUIDispose(){
        this.dispose();
    }
    public static void main(String[] args){
        try{
            SignUpUI signUpUI = new SignUpUI();
            signUpUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            signUpUI.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
