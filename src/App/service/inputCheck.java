package App.service;

import App.entity.User;
public class inputCheck {

    /**
     * 对注册的输入进行限制
     * Name 2<=4
     * userName 1<=16
     * Password 6 < 16 只能包含_， 数字，字母
     * email *****@****.**
     * phone 11位 可为空
     * sex不为空
     * @param user
     * @return
     */
    public static boolean checkSignUp(User user){
        if(user.getName().length() < 2 || user.getName().length() > 4){
            System.out.println("姓名长度不对");
            return false;
        }
        if(user.getUserName().length() < 1 || user.getUserName().length() > 16){
            System.out.println("用户名长度不对");
            return false;
        }
        if(user.getEmail().length() <= 30){
            int pos = user.getEmail().indexOf('@');

            if(pos == -1 || user.getEmail().indexOf('.',pos) == -1){
                System.out.println("请输入正确邮箱");
                return false;
            }
        }else{
            System.out.println("邮箱长度过长");
        }

        if(user.getPhone().length() != 0 && user.getPhone().length() != 11){
            System.out.println(user.getPhone());
            System.out.println("手机长度不对");
            return false;
        }

        if(user.getPassword().length() < 6 || user.getPassword().length() > 16){
            System.out.println("密码长度不对");
            return false;
        }else{
            String tmp = user.getPassword();
            for(int i = 0;i < tmp.length();i++){
                char c = tmp.charAt(i);
                if(c != '_' && !Character.isLetterOrDigit(c)){
                    System.out.println("密码中含有非法字符");
                    return false;
                }
            }
        }
        return true;
    }
}
