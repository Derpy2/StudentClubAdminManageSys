package App.entity;

public class User { //用户实例
    private String uid;
    private String name;
    private String sex;
    private String cid;
    private String phone;
    private String email;
    private String userName;
    private int state;
    public User(){

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String uid, String name, String sex, String cid, String phone, String email, String userName, String password) {
        this.uid = uid;
        this.name = name;
        this.sex = sex;
        this.cid = cid;
        this.phone = phone;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
