package com.udbac.versionpublish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.service.UserService;
import com.udbac.versionpublish.util.ResponseData;

/**
 * @author dundun.wang
 * @date 2019/05/27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * User login.
     * 
     * @param user {@literal null}
     * @return {@link ResponseData}
     */
    @RequestMapping("/login")
    public ResponseData login(@RequestBody User user) {
        ResponseData response = userService.getUserByUsername(user);
        return response;
    }

    /**
     * 分页查找渠道，默认情况下从当前页<code>page=0</code>开始查找。
     * <p>
     * 默认分页大小设置为<code>size=10</code>
     * 
     * @param page 不能为{@literal null}
     * @return {@link ResponseData}
     */
    @RequestMapping("/findPage")
    public ResponseData findPage(@PageableDefault(page = 0, size = 10, sort = { "username" }) Pageable page) {
        ResponseData res = userService.findPagination(page);
        return res;
    }

    /**
     * Register user.
     * 
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public ResponseData register(@RequestBody User user) {
        ResponseData response = userService.insertUser(user);
        return response;
    }

    /**
     * Update user.
     * 
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public ResponseData update(@RequestBody User user) {
        ResponseData response = userService.updateUser(user);
        return response;
    }

    /**
     * Delete single user.
     * 
     * @param user
     * @return
     */
    @RequestMapping("/delete")
    public ResponseData delete(@RequestBody User user) {
        ResponseData response = userService.deleteUserById(user);
        return response;
    }

    /**
     * Reset password for user.
     * 
     * @param user
     * @return
     */
    @RequestMapping("/resetPassword")
    public ResponseData resetPassword(@RequestBody User user) {
        return userService.resetPassword(user);
    }
}
