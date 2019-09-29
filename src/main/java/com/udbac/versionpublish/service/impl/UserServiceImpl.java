package com.udbac.versionpublish.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.repository.UserRepository;
import com.udbac.versionpublish.service.UserService;
import com.udbac.versionpublish.util.JwtUtil;
import com.udbac.versionpublish.util.MD5Util;
import com.udbac.versionpublish.util.ResponseData;

/**
 * @author dundun.wang
 * @date 2019/05/27
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;
    @Value(value = "${config.password.default}")
    String defaultPassword;

    @Override
    public ResponseData getUserByUsername(User user) {
        ResponseData response = new ResponseData();
        // TODO Auto-generated method stub
        List<User> users = userRepository.findByUsername(user.getUsername());
        if (null != users && users.size() == 1) {
            // 获取登录密码
            String result = MD5Util.getMD5(user.getPassword());
            // 查找数据库用户
            User userData = users.get(0);
            // 匹配用户名和密码
            if (userData.getUsername().equals(user.getUsername()) && userData.getPassword().equals(result)) {
                // 用户名和密码正确,生成token
                String token = JwtUtil.createJWT(user.getUsername());
                userData.setToken(token);
                response.setCode(true);
                response.setMessage("登录成功!");
                response.setObject(userData);
            } else {
                // 用户名或密码错误
                response.setCode(false);
                response.setObject(user);
                response.setMessage("用户名或密码错误!");
            }
        } else {
            response.setCode(false);
            response.setMessage("用户不存在或用户错误!");
            response.setObject(user);
        }

        return response;
    }

    public ResponseData deleteUserById(User user) {
        ResponseData response = new ResponseData();
        userRepository.deleteById(user.getId());
        response.setCode(true);
        response.setMessage("删除成功!");
        return response;
    }

    public ResponseData insertUser(User user) {
        ResponseData response = new ResponseData();
        // 先查询下是否存在该用户
        List<User> users = userRepository.findByUsername(user.getUsername());
        if (null != users && users.size() > 0) {
            // 已存在则不添加直接提示
            response.setCode(ResponseData.FAILD);
            response.setMessage("该用户已经存在！");
        } else {
            // 添加用户
            user.setId(UUID.randomUUID().toString());
            // 默认密码
            user.setPassword(MD5Util.getMD5(defaultPassword));
            // 获取当前日期
            String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            user.setCreateTime(dateNow);
            user.setUpdateTime(dateNow);
            User saveUser = userRepository.save(user);
            if (null != saveUser) {
                response.setCode(ResponseData.SUCCESS);
                response.setMessage("添加成功！");
                response.setObject(saveUser);
            }
        }
        return response;
    }

    public ResponseData updateUser(User user) {
        ResponseData response = new ResponseData();
        // 先查询到该用户
        User oneUser = userRepository.getOne(user.getId());
        oneUser.setChinesename(user.getChinesename());
        oneUser.setEmail(user.getEmail());
        // oneUser.setPassword(MD5Util.getMD5(user.getPassword()));
        oneUser.setPhone(user.getPhone());
        oneUser.setProvince(user.getProvince());
        user.setUpdateTime(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // 判断是否更改了登录名
        if (null != oneUser.getUsername() && !oneUser.getUsername().equals(user.getUsername())) {
            // 更改用户名查询数据库中是否存在
            List<User> userList = userRepository.findByUsername(user.getUsername());
            if (null != userList && userList.size() > 0) {
                // 如果已经存在则提示
                response.setCode(ResponseData.FAILD);
                response.setMessage("用户名已经存在!");
            } else {
                oneUser.setUsername(user.getUsername());

                User responseUser = userRepository.save(oneUser);
                response.setCode(ResponseData.SUCCESS);
                response.setMessage("更新成功!");
                response.setObject(responseUser);
            }
        } else {
            User responseUser = userRepository.save(oneUser);
            response.setCode(ResponseData.SUCCESS);
            response.setMessage("更新成功!");
            response.setObject(responseUser);
        }

        return response;
    }

    public ResponseData findPagination(Pageable page) {
        ResponseData rd = new ResponseData();
        Page<User> vPage = userRepository.findAll(page);
        rd.setObject(vPage);
        rd.setCode(ResponseData.SUCCESS);
        rd.setObject(vPage);
        return rd;
    }

    public ResponseData resetPassword(User user) {
        ResponseData response = new ResponseData();
        User resetUser = userRepository.getOne(user.getId());
        resetUser.setPassword(MD5Util.getMD5(defaultPassword));
        User responseUser = userRepository.save(resetUser);
        if (null != responseUser) {
            response.setCode(true);
            response.setMessage("重置成功!");
        }
        return response;
    }

    public static void main(String[] args) {

        System.out.println(JwtUtil.createJWT("dun"));
    }
}