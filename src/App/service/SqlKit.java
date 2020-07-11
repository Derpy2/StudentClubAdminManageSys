package App.service;

import App.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class SqlKit extends ConnectDB{

    /**
     * 统计普通用户数量
     * @param user
     * @return
     */
    public int countUUser(User user){
        int cot = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select COUNT(*) as num from cUser where uid like 'U%'";
        conn = this.getConnection();
        try{
            ps = conn.prepareStatement(sql);
            System.out.println(user.getUserName());
            rs = ps.executeQuery();

            if(rs.next()){
                cot = rs.getInt(1);
                System.out.println(rs.getInt("num"));
            }
        }catch (SQLException e){
            System.out.println("countUUser1" + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("countUUser2" + e.getMessage());
            }
            this.closeConnection();
        }
        return cot;
    }

    /**
     * 插入用户
     * @param user
     * @param num
     */
    public void insertUser(User user, int num){
        String number = "0123456789";
        String s = new String();
        for(int i = 0;i < 9;i++){
            s = number.charAt(num % 10) + s;
            num /= 10;
        }
        s = 'U' + s;
        user.setUid(s);
        if(user.getPhone().equals("")){
            user.setPhone(null);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into cUser(uid, uSex, uName, uUserName, uPassword, uEmail,uTele) Values(?, ?, ?, ?, ?, ?, ?)";
        conn = this.getConnection();
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUid());
            ps.setString(2, user.getSex());
            ps.setString(3, user.getName());
            ps.setString(4, user.getUserName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getPhone());
            rs = ps.executeQuery();


        }catch (SQLException e){
            System.out.println("insertUser1" + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("insertUser2" + e.getMessage());
            }
            this.closeConnection();
        }
    }

    /**
     * 用户名重复使用检查
     */
    public boolean UserNameCheck(String userName){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select uid from cUser where uUserName = ?";
        conn = this.getConnection();

        boolean flag = false;
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            rs = ps.executeQuery();
            if(!rs.next()){
                flag = true;
            }
        }catch (SQLException e){
            System.out.println("UserNameCheck1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("UserNameCheck2: " + e.getMessage());
            }
            this.closeConnection();
        }
        if(!flag){
            JOptionPane.showMessageDialog(null,"用户名重复");
        }
        return flag;
    }


    /**
     * 用户信息更改
     */

    public void modifyUser(User user){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update cUser set uSex = ?, uName = ?, uUserName = ?, uPassword = ?, uEmail = ?, uTele = ? where uid = ?";
        conn = this.getConnection();


        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getSex());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());
            ps.setString(7,user.getUid());
            rs = ps.executeQuery();

        }catch (SQLException e){
            System.out.println("modifyUser1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("modifyUser2: " + e.getMessage());
            }
            this.closeConnection();
        }
    }


    /**
     * 查询显示全部社团
     */
    public DefaultTableModel searchClub(String clubName){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select cid, cName, uid, (select uName from cUser where cUser.uid = cClub.uid) as 'cLeaderName', cTime from cClub";
        if(!clubName.equals("")){
            sql += " where cName like '%" + clubName + "%'";
        }
        conn = this.getConnection();
        DefaultTableModel res = null;

        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector<Object> columns = new Vector<Object>();
            columns.add("社团编号");
            columns.add("社团名称");
            columns.add("社团团长ID");
            columns.add("团长姓名");
            columns.add("社团建立时间");
            res = new DefaultTableModel(columns, 0);
            while(rs.next()){
                Object cid = rs.getString("cid");
                Object cName = rs.getString("cName");
                Object uid = rs.getString("uid");
                Object cLeaderName = rs.getString("cLeaderName");
                Object cTime = rs.getString("cTime");

                Vector<Object> row = new Vector<Object>();
                row.add(cid);
                row.add(cName);
                row.add(uid);
                row.add(cLeaderName);
                row.add(cTime);
                res.addRow(row);
            }

        }catch (SQLException e){
            System.out.println("searchClub1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("searchClub2: " + e.getMessage());
            }
            this.closeConnection();
        }
        return res;
    }


    /**
     * 活动查询
     * @param aName
     * @return
     */

    public DefaultTableModel searchActivity(String aName){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select aid, aPlace, aContent, aPNum, aRigEndTime, aStartTime, aStatus, (select cName from cClub where cClub.cid = cActivity.cid) as 'cName', aReason from cActivity";
        if(!aName.equals("")){
            sql += " where aContent like '%" + aName + "%'";
        }
        conn = this.getConnection();
        DefaultTableModel res = null;

        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector<Object> columns = new Vector<Object>();
            columns.add("活动编号");
            columns.add("活动地点名称");
            columns.add("活动名称");
            columns.add("活动人数");
            columns.add("活动报名截止日期");
            columns.add("活动开始时间");
            columns.add("活动审核状态");
            columns.add("活动社团名");
            columns.add("申请理由");
            res = new DefaultTableModel(columns, 0);
            while(rs.next()){
                Object aid = rs.getString("aid");
                Object aPlace = rs.getString("aPlace");
                Object aContent = rs.getString("aContent");
                Object aPNum = rs.getString("aPNum");
                Object aRigEndTime = rs.getString("aRigEndTime");
                Object aStartTime = rs.getString("aStartTime");
                Object aStatus = rs.getString("aStatus");
                Object cName = rs.getString("cName");
                Object aReason = rs.getString("aReason");

                Vector<Object> row = new Vector<Object>();
                row.add(aid);
                row.add(aPlace);
                row.add(aContent);
                row.add(aPNum);
                row.add(aRigEndTime);
                row.add(aStartTime);
                row.add(aStatus);
                row.add(cName);
                row.add(aReason);
                res.addRow(row);
            }

        }catch (SQLException e){
            System.out.println("searchActivity1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("searchActivity2: " + e.getMessage());
            }
            this.closeConnection();
        }
        return res;
    }

    /**
     * 用户查询，管理员才可查询密码
     * @param tmpuName
     * @param sta
     * @return
     */

    public DefaultTableModel searchUser(String tmpuName, int sta){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select uid, uSex, uName, uUserName";
        String sql2 = ", uPassword ";
        String sql3 = " from cUser";
        if(sta == 3){
            sql += sql2;
        }
        sql += sql3;

        if(!tmpuName.equals("")){
            sql += " where uName like '%" + tmpuName + "%'";
        }
        conn = this.getConnection();
        DefaultTableModel res = null;
        Object uPassword = null;
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector<Object> columns = new Vector<Object>();
            columns.add("用户编号");
            columns.add("用户性别");
            columns.add("用户名称");
            columns.add("用户用户名");
            if(sta == 3) {
                columns.add("用户密码");
            }
            res = new DefaultTableModel(columns, 0);
            while(rs.next()){
                Object uid = rs.getString("uid");
                Object uSex = rs.getString("uSex");
                Object uName = rs.getString("uName");
                Object uUserName = rs.getString("uUserName");
                if(sta == 3) {
                    uPassword = rs.getString("uPassword");
                }

                Vector<Object> row = new Vector<Object>();
                row.add(uid);
                row.add(uSex);
                row.add(uName);
                row.add(uUserName);
                if(sta == 3) {
                    row.add(uPassword);
                }
                res.addRow(row);
            }

        }catch (SQLException e){
            System.out.println("searchUser1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("searchUser2: " + e.getMessage());
            }
            this.closeConnection();
        }
        return res;
    }

    public void joinClub(String uid, String cid, String reason){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into cClubApplication Values(? ,? , ? , '未审核')";

        conn = this.getConnection();

        try{

            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setString(2, cid);
            ps.setString(3, reason);
            ps.executeQuery();



        }catch (SQLException e){
            System.out.println("joinClub1: " + e.getMessage());
        }finally {
            try{
                //rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("joinClub2: " + e.getMessage());
            }
            this.closeConnection();
        }
    }


    /**
     *
     * 判断是否为社团团长
     * @param uid
     */

    public boolean isClubLeader(String uid){
        boolean flag = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select cid from cClub where uid = ?";

        conn = this.getConnection();

        try{

            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            rs = ps.executeQuery();
            if(rs.next()){
                flag = true;
            }

        }catch (SQLException e){
            System.out.println("isClubLeader1: " + e.getMessage());
        }finally {
            try{
                //rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("isClubLeader2: " + e.getMessage());
            }
            this.closeConnection();
        }
        return flag;
    }

    /**
     * 统计社团总数
     *
     */
    public int countClub(){
        int tot = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select COUNT(cid) from cClub";

        conn = this.getConnection();

        try{
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            if(rs.next()){
                tot = rs.getInt(1);
            }

        }catch (SQLException e){
            System.out.println("isClubLeader1: " + e.getMessage());
        }finally {
            try{
                //rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("isClubLeader2: " + e.getMessage());
            }
            this.closeConnection();
        }
        return tot;
    }

    public void applyClub(Club club){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into cClub Values(?, ?, ?, ?, ?, '未审核')";

        conn = this.getConnection();

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, club.getCid());
            ps.setString(2,club.getCName());
            ps.setString(3,club.getUid());
            ps.setDate(4,club.getcTime());
            ps.setString(5, club.getcReason());
            ps.executeQuery();

        }catch (SQLException e){
            System.out.println("applyClub1: " + e.getMessage());
        }finally {
            try{
                //rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("applyClub2: " + e.getMessage());
            }
            this.closeConnection();
        }
    }



    public static void main(String[] args){
        SqlKit tmp = new SqlKit();
        tmp.applyClub(new Config().tmpClub);
        //DefaultTableModel tmpmodel =
        //System.out.println(tmpmodel);
    }
}
