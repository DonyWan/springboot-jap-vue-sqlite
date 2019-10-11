package com.udbac.versionpublish.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.entity.Version;
import com.udbac.versionpublish.repository.UserRepository;
import com.udbac.versionpublish.repository.VersionRepository;
import com.udbac.versionpublish.service.VersionService;
import com.udbac.versionpublish.util.JwtUtil;
import com.udbac.versionpublish.util.ResponseData;

/**
 * @author dundun.wang
 * @date 2019/10/11
 */
@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionRepository versionRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseData insertVersion(Version version, String token) {
        // 获取用户名
        String userName = JwtUtil.parseJWT(token).getSubject();
        List<User> users = userRepository.findByUsername(userName);
        version.setCreateTime(LocalDateTime.now().toString());
        version.setUpdateTime(LocalDateTime.now().toString());
        version.setId(UUID.randomUUID().toString());
        version.setUser(users.get(0));
        versionRepository.save(version);
        ResponseData rd = new ResponseData();
        rd.setCode(ResponseData.SUCCESS);
        return rd;
    }

    public ResponseData deleteVersion(Version version, String token) {
        ResponseData rd = new ResponseData();
        try {
            versionRepository.deleteById(version.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        rd.setCode(ResponseData.SUCCESS);
        return rd;
    }

    public ResponseData updateVersion(Version version, String token) {
        // 获取请求的token
//        String token = version.getHeader("token");
        // 获取用户id
        String userId = JwtUtil.parseJWT(token).getSubject();
//        List<User> users = userRepository.findByUsername(userName);
        User user = userRepository.getOne(userId);
        ResponseData rd = ResponseData.getInstance();
        Version versionOne = versionRepository.getOne(version.getId());
        versionOne.setNotes(version.getNotes());
        versionOne.setUpdateTime(LocalDateTime.now().toString());
        versionOne.setVersionNumber(version.getVersionNumber());
        versionOne.setVersionDate(version.getVersionDate());
        versionOne.setProvince(version.getProvince());
        versionOne.setUser(user);
        versionOne.setBranch(version.getBranch());
        versionRepository.save(versionOne);
        return rd;
    }

    public ResponseData findPagination(Pageable page) {
        ResponseData responseData = new ResponseData();
        Page<Version> versionPage = versionRepository.findAll(page);
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setObject(versionPage);
        return responseData;
    }
}
