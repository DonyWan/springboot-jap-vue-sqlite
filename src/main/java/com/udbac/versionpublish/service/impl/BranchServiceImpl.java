package com.udbac.versionpublish.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udbac.versionpublish.entity.Branch;
import com.udbac.versionpublish.repository.BranchRepository;
import com.udbac.versionpublish.service.BranchService;
import com.udbac.versionpublish.util.ResponseData;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    BranchRepository branchRepository;

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
                //branchRepository.findByNameAndDcsidAndProvince(branch);
        if (null != branchs && branchs.size() > 0) {
            // 已存在则不添加直接提示
            response.setCode(ResponseData.FAILD);
            response.setMessage("该渠道已经存在!");
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
                response.setMessage("添加成功!");
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
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.udbac.versionpublish.service.BranchService#findPagination(org.
     * springframework.data.domain.Pageable)
     */
    @Override
    public ResponseData findPagination(Pageable page) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.udbac.versionpublish.service.BranchService#deleteById(com.udbac.
     * versionpublish.entity.Branch)
     */
    @Override
    public ResponseData deleteById(Branch branch) {
        return null;
    }

}
