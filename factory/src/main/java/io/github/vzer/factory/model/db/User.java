package io.github.vzer.factory.model.db;

/**
 * 用户基本信息表
 *
 * @author: Vzer.
 * @date: 2017/7/25. 16:43
 * @email: vzer@qq.com
 */

public class User {
    public static final int SEX_MAN = 1;
    public static final int SEX_WOMAN = 2;


    private String name; //用户名
    private String portrait; //头像url
    private String realName;//真实姓名
    private String mobile; //手机号
    private int sex = 0; //性别
    private int credits = 0; // 信用积分
    private String pushId;  // 设备的推送Id
    private boolean isBind;// 设备Id是否已经绑定到了服务器
    private String token; // 登录状态的Token，用来接口请求


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", portrait='" + portrait + '\'' +
                ", sex=" + sex +
                ", credits=" + credits +
                '}';
    }
}
