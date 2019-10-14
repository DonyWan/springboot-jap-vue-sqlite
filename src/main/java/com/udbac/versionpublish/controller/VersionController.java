package com.udbac.versionpublish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.udbac.versionpublish.entity.Version;
import com.udbac.versionpublish.service.VersionService;
import com.udbac.versionpublish.util.ResponseData;

/**
 * 
 * @author dundun.wang
 * @date 2019/10/11
 */
@Controller
@RequestMapping("/version")
public class VersionController {
    @Autowired
    private VersionService versionService;

    /**
     * 
     * @param version
     * @param token
     * @return {@link ResponseData}
     */
    @RequestMapping("/createVersion")
    @ResponseBody
    public ResponseData insertVersion(@RequestBody Version version, @RequestHeader String token) {
        ResponseData responseData = versionService.insertVersion(version, token);
        return responseData;
    }

    /**
     * 
     * @param version
     * @param token
     * @return {@link ResponseData}
     */
    @ResponseBody
    @RequestMapping("/deleteVersion")
    public ResponseData deleteVersion(@RequestBody Version version, @RequestHeader String token) {
        ResponseData responseData = versionService.deleteVersion(version, token);
        return responseData;
    }

    /**
     * 
     * @param version
     * @param token
     * @return {@link ResponseData}
     */
    @ResponseBody
    @RequestMapping("/updateVersion")
    public ResponseData updateVersion(@RequestBody Version version, @RequestHeader String token) {
        ResponseData responseData = versionService.updateVersion(version, token);
        return responseData;
    }

    /**
     * 分页查找
     * @param page
     * @return {@link ResponseData}
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public ResponseData findPagination(@PageableDefault(page = 0, size = 10, sort = { "versionDate" }) Pageable page) {
        ResponseData responseData = versionService.findPagination(page);
        return responseData;
    }
    
    /**
     * 上传文件
     * @param file
     * @param version
     * @return
     */
    @ResponseBody
    @PostMapping("/upload")
    public ResponseData handleFileUpload(@RequestPart MultipartFile file, String version) {
        ResponseData responseData = versionService.uploadFile(file, version);
        return responseData;
    }

}
