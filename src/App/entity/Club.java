package App.entity;

public class Club {//社团实例
    private String cid;
    private String cName;
    private String uid;
    private String cTime;
    private String cReason;

    public Club(){

    }

    public Club(String cid, String cName, String uid, String cTime, String cReason) {
        this.cid = cid;
        this.cName = cName;
        this.uid = uid;
        this.cTime = cTime;
        this.cReason = cReason;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cname) {
        this.cName = cname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getcReason() {
        return cReason;
    }

    public void setcReason(String cReason) {
        this.cReason = cReason;
    }
}
