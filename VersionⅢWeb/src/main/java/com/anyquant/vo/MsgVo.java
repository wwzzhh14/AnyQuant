package com.anyquant.vo;


import com.anyquant.model.User;

/**
 * Created by Jiayiwu on 16/6/8.
 */
public class MsgVo {

    private boolean success =false;
    private String  result = "";
    private User  user  =new User();

    public MsgVo() {
    }

    public MsgVo(boolean success, String result, User user) {
        this.success = success;
        this.result = result;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
