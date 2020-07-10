package App.UI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import App.entity.User;
import App.entity.Config;
import App.service.inputCheck;
import App.service.SqlKit;


public class ModifyInfoUI extends JDialog{

    private final JPanel modifyInfoPanel = new JPanel();
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

    private JButton save = new JButton();
    private JButton cancel = new JButton();


    /**
     * 信息编辑界面
     */

    ModifyInfoUI(User user){
        this.setTitle("信息编辑");
        setBounds(100,100, 350, 600);
        getContentPane().setLayout(new BorderLayout());
        modifyInfoPanel.setBorder(new EmptyBorder(10,10,10,10));
        getContentPane().add(modifyInfoPanel, BorderLayout.CENTER);
        modifyInfoPanel.setLayout(null);

        userNameLabel.setText("\u7528\u6237\u540d\uff1a");//用户名
        userNameLabel.setBounds(65,44,70,16);
        modifyInfoPanel.add(userNameLabel);

        passwordLabel.setText("\u5BC6\u7801\uFF1A");//密码
        passwordLabel.setBounds(65,90,70,16);
        modifyInfoPanel.add(passwordLabel);

        passwordLabel2.setText("重复密码：");
        passwordLabel2.setBounds(65,136,70,16);
        modifyInfoPanel.add(passwordLabel2);

        name.setText("姓名:");
        name.setBounds(65,182,70,16);
        modifyInfoPanel.add(name);

        sex.setText("性别：");
        sex.setBounds(65,228,70,16);
        modifyInfoPanel.add(sex);

        email.setText("邮箱：");
        email.setBounds(65,274,70,16);
        modifyInfoPanel.add(email);

        phone.setText("电话：");
        phone.setBounds(65,320,70,16);
        modifyInfoPanel.add(phone);

        userName.setBounds(126,44,116,22);//用户名输入
        modifyInfoPanel.add(userName);
        userName.setColumns(20);
        userName.setText(user.getUserName());

        password.setBounds(126,90,116,22);//密码输入
        password.setColumns(20);
        modifyInfoPanel.add(password);
        password.setText(user.getPassword());

        password2.setBounds(126,136,116,22);//密码二次输入
        password2.setColumns(20);
        modifyInfoPanel.add(password2);
        password2.setText(user.getPassword());

        nameField.setBounds(126,182,116,22);//姓名
        nameField.setColumns(20);
        modifyInfoPanel.add(nameField);
        nameField.setText(user.getName());

        buttonGroup.add(sex1);//性别单选按钮
        buttonGroup.add(sex2);
        sex1.setText("男");
        sex2.setText("女");
        butPanel.add(sex1);
        butPanel.add(sex2);
        sex1.setSelected(true);
        butPanel.setLayout(new FlowLayout((FlowLayout.LEFT)));
        butPanel.setBounds(126,220,116,30);
        modifyInfoPanel.add(butPanel);
        if(user.getSex().equals("男")){
            sex1.setSelected(true);
        }else{
            sex2.setSelected(true);
        }

        emailfield.setBounds(126,274,116,22);//邮箱
        emailfield.setColumns(20);
        modifyInfoPanel.add(emailfield);
        emailfield.setText(user.getEmail());


        phoneField.setBounds(126,320,116,22);//电话
        phoneField.setColumns(20);
        modifyInfoPanel.add(phoneField);
        phoneField.setText(user.getPhone());

        hint.setText("<html><HTML><body>* 名字 2<=4个字符 <br>" +
                " * 用户名 1<=16个字符<br>" +
                "* 密码 6 < 16位 只能包含_, 数字, 字母<br>" +
                "* 邮箱为*****@****.**格式，最大30位<br>" +
                "* phone 11位 可为空<br>" +
                "* sex不为空</body></html>");
        hint.setBounds(65,330,250,200);
        modifyInfoPanel.add(hint);


        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);



        save.setText("保存");//保存
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd2 = String.valueOf(password2.getPassword());//密码与电话检查
                String phone = phoneField.getText();
                if(phone.equals("")){
                    phone = null;
                }
                User tmpuser = new User(user.getUid(), nameField.getText(),  sex1.isSelected() ? "男" : "女", user.getCid(),phone,emailfield.getText(), userName.getText(),String.valueOf(password.getPassword()));

                if(pwd2.equals(tmpuser.getPassword()) && inputCheck.checkSignUp(tmpuser)){
                    SqlKit sql1 = new SqlKit();
                    sql1.modifyUser(tmpuser);
                    JOptionPane.showMessageDialog(null, "保存成功");
                    ModifyInfoUIDispose();
                }else{
                    JOptionPane.showMessageDialog(null, "请检查输入数据格式是否正确");
                }

            }
        });
        buttonPanel.add(save);


        cancel.setText("取消");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyInfoUIDispose();
            }
        });
        buttonPanel.add(cancel);
    }

    private void ModifyInfoUIDispose(){
        this.dispose();
    }
    public static void main(String[] args){
        try{
            ModifyInfoUI modifyInfoUI = new ModifyInfoUI(new Config().tmpUser);
            modifyInfoUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            modifyInfoUI.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
