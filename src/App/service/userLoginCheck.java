package App.service;
import java.sql.*;
import App.entity.User;

public class userLoginCheck extends ConnectDB{
    public boolean LoginCheck(User user){//登陆检查
        boolean flag = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from cUser where uUserName = ? and uPassword = ?";
        conn = this.getConnection();
        try{
            ps = conn.prepareStatement(sql);
            System.out.println(user.getUserName());
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();

            if(rs.next()){
                flag = true;
            }
        }catch (SQLException e){
            System.out.println("UserLoginChekc1" + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("UserLoginCheck2" + e.getMessage());
            }
            this.closeConnection();
        }
        return flag;
    }

    public User getUserInfo(User user){
        User tmp = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from cUser where uUserName=? and uPassword=?";
        conn = this.getConnection();
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){
                tmp.setUid(rs.getString(0));
                tmp.setSex(rs.getString(1));
                tmp.setName(rs.getString(2));
                tmp.setEmail(rs.getString(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            this.closeConnection();
        }
        return tmp;
    }
}
