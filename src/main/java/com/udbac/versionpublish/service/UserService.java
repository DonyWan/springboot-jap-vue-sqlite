package com.udbac.versionpublish.service;

import org.springframework.data.domain.Pageable;

import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.util.ResponseData;

/**
 * @author dundun.wang
 * @date 2019/05/27
 */
public interface UserService {
    ResponseData getUserByUsername(User user);

    ResponseData insertUser(User user);

    ResponseData updateUser(User user);

    ResponseData findPagination(Pageable page);

    ResponseData deleteUserById(User user);

    ResponseData resetPassword(User user);
}
