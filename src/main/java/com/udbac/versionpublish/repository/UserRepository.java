package com.udbac.versionpublish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udbac.versionpublish.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	List<User> findByUsername(String username);
}
