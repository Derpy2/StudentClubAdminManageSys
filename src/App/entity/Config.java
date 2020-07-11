package App.entity;

import java.util.Date;


public class Config {
    public Date date = new Date();
    public java.sql.Date localDate = new java.sql.Date(date.getTime());
    public final User tmpUser = new User("U000000004","呢份","男", null, "12345663437","klsjdfnvoir@163.com","Admin","123456");
    public final User basicUser = new User("U000000002","灰喉","女",null,"12346876121","81523424@qq.com","huih","123456");
    public static final String number = "0123456789";
    public final Club tmpClub = new Club("0000000005", "社团", "U000000003", localDate,"无");
}
