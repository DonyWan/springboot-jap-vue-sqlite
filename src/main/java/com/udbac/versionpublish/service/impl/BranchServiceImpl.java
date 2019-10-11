package com.udbac.versionpublish.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.udbac.versionpublish.entity.Branch;
import com.udbac.versionpublish.entity.QBranch;
import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.enums.Admin;
import com.udbac.versionpublish.repository.BranchRepository;
import com.udbac.versionpublish.repository.UserRepository;
import com.udbac.versionpublish.service.BranchService;
import com.udbac.versionpublish.util.JwtUtil;
import com.udbac.versionpublish.util.ResponseData;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    UserRepository userRepository;

    /*
     * (non-Javadoc)
     * 
     * @see com.udbac.versionpublish.service.BranchService#insert(com.udbac.
     * versionpublish.entity.Branch)
     */
    @Override
    public ResponseData insert(Branch branch) {
        ResponseData response = new ResponseData();
        // 先查询下当前省份下是否包含相同名称和dcsid
        List<Branch> branchs = new ArrayList<Branch>();
        // branchRepository.findByNameAndDcsidAndProvince(branch);
        if (null != branchs && branchs.size() > 0) {
            // 已存在则不添加直接提示
            response.setCode(ResponseData.FAILD);
            response.setMessage("已经存在,请勿重复添加.");
        } else {
            // 获取当前日期
            String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // 添加渠道
            branch.setId(UUID.randomUUID().toString());
            branch.setCreateTime(dateNow);
            branch.setUpdateTime(dateNow);
            Branch responseBranch = branchRepository.save(branch);
            if (null != responseBranch) {
                response.setCode(ResponseData.SUCCESS);
                response.setMessage("添加成功.");
                response.setObject(responseBranch);
            }
        }
        return response;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.udbac.versionpublish.service.BranchService#update(com.udbac.
     * versionpublish.entity.Branch)
     */
    @Override
    public ResponseData update(Branch branch) {
        // 返回类型
        ResponseData responseData = new ResponseData();
        // 查找是否重复
        List<Branch> branchs = branchRepository.findByNameAndDcsidAndProvince(branch);
        if (null != branchs && branchs.size() > 0) {
            // 存在则提示
            responseData.setCode(ResponseData.FAILD);
            responseData.setMessage("已经存在,请重新更改.");
        } else {
            // 更新操作先做查找
            Branch branchOne = branchRepository.getOne(branch.getId());

            // 当前时间
            String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            branchOne.setDcsid(branch.getDcsid());
            branchOne.setName(branch.getName());
            branchOne.setProvince(branch.getProvince());
            branchOne.setUpdateTime(dateNow);

            try {
                Branch branchResponse = branchRepository.save(branchOne);
                responseData.setCode(ResponseData.SUCCESS);
                responseData.setMessage("更改成功.");
                responseData.setObject(branchResponse);
            } catch (Exception e) {
                responseData.setCode(ResponseData.FAILD);
                responseData.setMessage(e.getMessage());
            }
        }

        return responseData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.udbac.versionpublish.service.BranchService#findPagination(org.
     * springframework.data.domain.Pageable)
     */
    @Override
    public ResponseData findPagination(Pageable page) {
        ResponseData responseData = ResponseData.getInstance();
        try {
            Page<Branch> branchPage = branchRepository.findAll(page);
            responseData.setCode(ResponseData.SUCCESS);
            responseData.setObject(branchPage);
        } catch (Exception e) {
            responseData.setCode(ResponseData.FAILD);
            responseData.setMessage(e.getMessage());
        }
        return responseData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.udbac.versionpublish.service.BranchService#deleteById(com.udbac.
     * versionpublish.entity.Branch)
     */
    @Override
    public ResponseData deleteById(Branch branch) {
        ResponseData responseData = new ResponseData();
        try {
            branchRepository.deleteById(branch.getId());
            responseData.setCode(ResponseData.SUCCESS);
            responseData.setMessage("删除成功.");
        } catch (Exception e) {
            responseData.setCode(ResponseData.FAILD);
            responseData.setMessage(e.getMessage());
        }
        return responseData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.udbac.versionpublish.service.BranchService#findByProvince(java.lang.
     * String)
     */
    @Override
    public ResponseData findByProvince(String token) {
        ResponseData responseData = ResponseData.getInstance();
        // 获取当前用户id
        String userId = JwtUtil.parseJWT(token).getSubject();
        // 获取当前用户
        User user = userRepository.getOne(userId);
        // 如果是管理员查询所有
        if (Admin.YES.getValue().equals(user.getAdmin())) {
            List<Branch> branchs = branchRepository.findAll();
            responseData.setCode(ResponseData.SUCCESS);
            responseData.setObject(branchs);
        } else {
            Predicate predicate = QBranch.branch.province.eq(user.getProvince());
            try {
                Iterable<Branch> branchs = branchRepository.findAll(predicate);
                responseData.setCode(ResponseData.SUCCESS);
                responseData.setObject(branchs);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                responseData.setCode(ResponseData.FAILD);
                responseData.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }

        return responseData;
    }

}
