package com.udbac.versionpublish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udbac.versionpublish.entity.Branch;
import com.udbac.versionpublish.service.BranchService;
import com.udbac.versionpublish.util.ResponseData;

@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    BranchService branchService;

    /**
     * 添加渠道
     * 
     * @param branch
     * @return {@link ResponseData}
     */
    public ResponseData insert(@RequestBody Branch branch) {
        return branchService.insert(branch);
    }
}
