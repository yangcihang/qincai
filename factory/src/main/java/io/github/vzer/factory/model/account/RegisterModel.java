package io.github.vzer.factory.model.account;

/**
 * 用户注册 请求model
 * @author: Vzer.
 * @date: 2017/7/25. 20:58
 * @email: vzer@qq.com
 */

public class RegisterModel {
    private String mobile; //手机号
    private String password; //密码
    //private String code; //验证码， 后需添加
    private String  name;//昵称
    private String real_name;//真实姓名
    private Integer sex;//性别
    private Integer credits;//信用积分

    public RegisterModel(String phone, String password) {
        this.mobile = phone;
        this.password = password;
        this.name = phone;//默认昵称为手机号
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
