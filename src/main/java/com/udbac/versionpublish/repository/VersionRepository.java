package com.udbac.versionpublish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udbac.versionpublish.entity.Version;

public interface VersionRepository extends JpaRepository<Version, String>{

}
