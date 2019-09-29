package com.udbac.versionpublish.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.entity.Version;
import com.udbac.versionpublish.repository.UserRepository;
import com.udbac.versionpublish.repository.VersionRepository;
import com.udbac.versionpublish.util.JwtUtil;
import com.udbac.versionpublish.util.Pagination;
import com.udbac.versionpublish.util.ResponseData;

/**
 * @author dundun.wang
 * @date 2019-06-01
 * @Description
 */
@Controller
@RequestMapping("/version")
public class VersionController {
    @Resource
    private VersionRepository vr;
    @Autowired
    private UserRepository userRepository;

    /**
     * @author dundun.wang
     * @param v
     * @return
     */
    @RequestMapping("/createVersion")
    @ResponseBody
    public ResponseData insertVersion(@RequestBody Version v) {
        v.setCreateTime(LocalDateTime.now().toString());
        v.setUpdateTime(LocalDateTime.now().toString());
        v.setId(UUID.randomUUID().toString());
        vr.save(v);
        ResponseData rd = new ResponseData();
        rd.setCode(ResponseData.SUCCESS);
        return rd;
    }

    /**
     * @author dundun.wang
     * @param v
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public List<Version> findAll() {
        List<Version> vs = vr.findAll();
        return vs;
    }

    /**
     * @author dundun.wang
     * @param v
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteVersion")
    public ResponseData deleteVersion(@RequestBody Version v) {
        ResponseData rd = new ResponseData();
        try {
            vr.deleteById(v.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        rd.setCode(ResponseData.SUCCESS);
        return rd;
    }

    /**
     * @author dundun.wang
     * @param v
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateVersion")
    public ResponseData updateVersion(@RequestBody Version v, HttpServletRequest reques) {
        // 获取请求的token
        String token = reques.getHeader("token");
        // 获取用户名
        String userName = JwtUtil.parseJWT(token).getSubject();
        List<User> users = userRepository.findByUsername(userName);
        if (users.size() > 0) {
            // users.get(0).getUsername();
        }
        ResponseData rd = new ResponseData();
        Version version = vr.getOne(v.getId());
        version.setUpdateTime(LocalDateTime.now().toString());
        version.setNotes(v.getNotes());
        version.setVersionNumber(v.getVersionNumber());
        version.setVersionDate(v.getVersionDate());
        version.setProvince(v.getProvince());
        version.setUser(users.get(0).getUsername());
        vr.save(version);
        return rd;
    }

    /**
     * @author dundun.wang
     * @param v
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<Version> findPagination(@RequestBody Pagination p) {
        ResponseData rd = new ResponseData();
        Pageable pageable = PageRequest.of(p.getCurrentPage() - 1, p.getPageSize());
        Page<Version> vPage = vr.findAll(pageable);
        // 如果该页没有数据则跳转上一页
        if (vPage.getContent().size() == 0 && vPage.getTotalPages() != 0) {
            int currentPage = vPage.getTotalPages() - 1;
            Pageable page = PageRequest.of(currentPage, p.getPageSize());
            Page<Version> v = vr.findAll(page);
            return v;
        }
        rd.setCode(ResponseData.SUCCESS);
        rd.setObject(vPage);
        return vPage;
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
