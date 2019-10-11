package com.udbac.versionpublish.service;

import org.springframework.data.domain.Pageable;

import com.udbac.versionpublish.entity.Version;
import com.udbac.versionpublish.util.ResponseData;

/**
 * @author dundun.wang
 * @date 2019/10/11
 */
public interface VersionService {
    public ResponseData insertVersion(Version version,String token);
    public ResponseData deleteVersion(Version version,String token);
    public ResponseData updateVersion(Version version,String token);
    public ResponseData findPagination(Pageable page);
}
