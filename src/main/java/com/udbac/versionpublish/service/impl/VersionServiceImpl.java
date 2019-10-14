package com.udbac.versionpublish.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.entity.Version;
import com.udbac.versionpublish.enums.Provinces;
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
        String userId = JwtUtil.parseJWT(token).getSubject();
        User user = userRepository.findById(userId).get();
        version.setCreateTime(LocalDateTime.now().toString());
        version.setUpdateTime(LocalDateTime.now().toString());
        version.setId(UUID.randomUUID().toString());
        version.setUser(user);
        version.setBranch(version.getBranch());
        Version returnVersion = versionRepository.save(version);
        ResponseData rd = new ResponseData();
        rd.setCode(ResponseData.SUCCESS);
        rd.setObject(returnVersion);
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

    public ResponseData uploadFile(MultipartFile file, String version) {
        ResponseData responseData = ResponseData.getInstance();
        if (!file.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            // 允许为null的属性转换
            mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            try {
                // json字符串转换成对象
                Version versionObject = mapper.readValue(version, Version.class);
                URL path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX);

                String province = versionObject.getBranch().getProvince();
                // 重新更改文件名为省份-渠道-版本名称-时间秒
                // 省份代码
                String provinceCode = Arrays.asList(Provinces.values()).stream()
                        .filter(param -> param.name().equals(province)).findAny().get().getValue();
                // 渠道名
                String dcsid = versionObject.getBranch().getDcsid();
                // 版本号
                String versionNumber = versionObject.getVersionNumber();
                // 当前时间到秒
                String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String fileName = provinceCode + "-" + dcsid + "-" + versionNumber + "-" + dateTime + ".js";
                File saveFile = new File(path.getPath(), "/static/provinces/" + provinceCode);
                if (!saveFile.exists()) {
                    saveFile.mkdirs();
                }
                File save = new File(saveFile, fileName);

                file.transferTo(save);
                String uploadDir = "/provinces/" + provinceCode + "/" + fileName;
                // 更新版本里的下载路径
                Version updateVersion = versionRepository.getOne(versionObject.getId());
                updateVersion.setUploadDir(uploadDir);
                versionRepository.save(updateVersion);
                responseData.setCode(ResponseData.SUCCESS);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            responseData.setCode(ResponseData.FAILD);
            responseData.setMessage("文件为空.");
        }
        return responseData;
    }

}
