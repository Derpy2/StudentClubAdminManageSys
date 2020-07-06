package App.entity;

public class Login { //登陆实例
    private int lid;
    private String logName;
    private String logPwd;
    private int isLogin;
    private int state;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getLogPwd() {
        return logPwd;
    }

    public void setLogPwd(String logPwd) {
        this.logPwd = logPwd;
    }

    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
