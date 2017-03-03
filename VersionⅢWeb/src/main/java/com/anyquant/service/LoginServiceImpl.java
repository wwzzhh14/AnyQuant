package com.anyquant.service;

import com.anyquant.dao.BaseDao;
import com.anyquant.model.User;
import com.anyquant.utils.SHA256;
import com.anyquant.vo.MsgVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 16/6/8.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    BaseDao baseDao;

    public MsgVo login(String username, String password) {
        List<User> user = (List<User>)baseDao.findByProperty(User.class,"username",username);
        if(user==null||user.size()<=0)
            return new MsgVo(false,"用户名或密码错误",null);
        User u = user.get(0);
        if(!(u.getPassword().equals(SHA256.encrypt(password)))){
            return new MsgVo(false,"用户名或密码错误",null);
        }

        return new MsgVo(true,"success",u);
    }

    public MsgVo register(String username, String usercode, String userpass, String userpass2) {

        if(usercode.length()<3){
            return new MsgVo(false,"用户名长度需要大于等于3",null);
        }
        if(!userpass.equals(userpass2)){
            return new MsgVo(false,"两次密码不一致",null);
        }
        List<User> user = (List<User>)baseDao.findByProperty(User.class,"username",username);
        if(user!=null&&user.size()>0){
            return new MsgVo(false,"用户名已存在",null );
        }

        User u = new User();
        u.setUsername(usercode);
        u.setPassword(SHA256.encrypt(userpass));
        u.setName(username);
        baseDao.save(u);

        return new MsgVo(true,"注册成功",u);
    }



}
