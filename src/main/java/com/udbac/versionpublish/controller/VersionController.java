package com.udbac.versionpublish.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
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
     * 
     * @param page
     * @return {@link ResponseData}
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public ResponseData findPagination(@PageableDefault(page = 0, size = 10, sort = { "versionDate" }) Pageable page) {
        ResponseData responseData = versionService.findPagination(page);
        return responseData;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestPart("file") MultipartFile file, @RequestBody Version v) {
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file.getOriginalFilename())));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }

            return "上传成功";

        } else {
            return "上传失败，因为文件是空的.";
        }
    }
}
