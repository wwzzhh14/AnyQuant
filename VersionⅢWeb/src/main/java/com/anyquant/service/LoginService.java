package com.anyquant.service;

import com.anyquant.vo.MsgVo;


/**
 * Created by Jiayiwu on 16/6/8.
 */
public interface LoginService {

    public MsgVo login(String username, String password);

    public MsgVo register(String username,String usercode,String userpass,String userpass2);

}
