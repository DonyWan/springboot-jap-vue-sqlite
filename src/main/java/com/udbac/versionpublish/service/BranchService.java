package com.udbac.versionpublish.service;

import org.springframework.data.domain.Pageable;

import com.udbac.versionpublish.entity.Branch;
import com.udbac.versionpublish.util.ResponseData;

public interface BranchService {
    /**
     * 添加渠道
     * 
     * @param branch
     * @return {@link ResponseData}
     */
    ResponseData insert(Branch branch);

    /**
     * 更新渠道
     * 
     * @param branch
     * @return {@link ResponseData}
     */
    ResponseData update(Branch branch);

    /**
     * 分页查找
     * 
     * @param page
     * @return {@link ResponseData}
     */
    ResponseData findPagination(Pageable page);

    /**
     * 根据ID删除渠道，这里是真删除
     * 
     * @param branch
     * @return {@link ResponseData}
     */
    ResponseData deleteById(Branch branch);
    /**
     * 根据用户省份查找渠道
     * @param province
     * @return {@link ResponseData}
     */
    ResponseData findByProvince(String token);

}
